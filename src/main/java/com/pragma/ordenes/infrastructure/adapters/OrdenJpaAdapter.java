package com.pragma.ordenes.infrastructure.adapters;


import com.pragma.ordenes.domain.model.EstadoOrden;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import com.pragma.ordenes.infrastructure.persistence.OrdenEntity;
import com.pragma.ordenes.infrastructure.persistence.OrdenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class OrdenJpaAdapter implements OrdenRepositoryPort {

    private static final Logger log = LoggerFactory.getLogger(OrdenJpaAdapter.class);

    private final OrdenRepository ordenRepository;
    private final OrdenMapper ordenMapper;

    public OrdenJpaAdapter(OrdenRepository ordenRepository, OrdenMapper ordenMapper) {
        this.ordenRepository = ordenRepository;
        this.ordenMapper = ordenMapper;
    }

    @Override
    public Orden guardar(Orden orden) {
        log.info("Guardando orden para cliente: {}", orden.getClienteId());
        
        if (orden.getId() == null) {
            orden = new Orden(
                    UUID.randomUUID(),
                    orden.getClienteId(),
                    orden.getItems(),
                    orden.getEstado(),
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );
        }

        OrdenEntity entity = ordenMapper.toEntity(orden);
        OrdenEntity savedEntity = ordenRepository.save(entity);
        log.info("Orden guardada exitosamente con ID: {}", savedEntity.getId());
        
        return ordenMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Orden> buscarPorId(UUID id) {
        log.debug("Buscando orden por ID: {}", id);
        return ordenRepository.findById(id)
                .map(ordenMapper::toDomain);
    }

    @Override
    public List<Orden> buscarPorClienteId(String clienteId) {
        log.debug("Buscando órdenes para cliente: {}", clienteId);
        return ordenRepository.findByClienteId(clienteId)
                .stream()
                .map(ordenMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Orden> buscarPorEstado(Orden.EstadoOrden estado) {
        log.debug("Buscando órdenes por estado: {}", estado);
        return ordenRepository.findByEstado(estado)
                .stream()
                .map(ordenMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Orden actualizarEstado(UUID id, Orden.EstadoOrden nuevoEstado) {
        log.info("Actualizando estado de orden {} a {}", id, nuevoEstado);
        
        Optional<OrdenEntity> entityOpt = ordenRepository.findById(id);
        if (entityOpt.isEmpty()) {
            log.error("Orden no encontrada para actualización: {}", id);
            throw new IllegalArgumentException("Orden no encontrada con ID: " + id);
        }
        
        OrdenEntity entity = entityOpt.get();
        entity.setEstado(nuevoEstado);
        entity.setFechaActualizacion(LocalDateTime.now());
        
        OrdenEntity savedEntity = ordenRepository.save(entity);
        log.info("Estado de orden actualizado exitosamente");
        
        return ordenMapper.toDomain(savedEntity);
    }

    public List<Orden> buscarTodas() {
        log.debug("Buscando todas las órdenes");
        return ordenRepository.findAll()
                .stream()
                .map(ordenMapper::toDomain)
                .collect(Collectors.toList());
    }

    public void eliminar(UUID id) {
        log.info("Eliminando orden: {}", id);
        ordenRepository.deleteById(id);
    }
}