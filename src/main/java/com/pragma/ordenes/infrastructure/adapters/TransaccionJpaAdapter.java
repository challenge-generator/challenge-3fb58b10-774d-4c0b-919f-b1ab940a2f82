package com.pragma.ordenes.infrastructure.adapters;

import com.pragma.ordenes.domain.model.Transaccion;
import com.pragma.ordenes.domain.ports.TransaccionRepositoryPort;
import com.pragma.ordenes.infrastructure.persistence.entity.TransaccionEntity;
import com.pragma.ordenes.infrastructure.persistence.repository.TransaccionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransaccionJpaAdapter implements TransaccionRepositoryPort {

    private final TransaccionRepository transaccionRepository;

    public TransaccionJpaAdapter(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Transaccion guardar(Transaccion transaccion) {
        if (transaccion.getId() == null) {
            transaccion.actualizarId(UUID.randomUUID());
        }
        if (transaccion.getFechaTransaccion() == null) {
            transaccion.actualizarFechaTransaccion(LocalDateTime.now());
        }
        TransaccionEntity entity = toEntity(transaccion);
        TransaccionEntity savedEntity = transaccionRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Transaccion> buscarPorId(UUID id) {
        return transaccionRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Transaccion> buscarPorEntidadAfectadaYEntidadId(String entidadAfectada, UUID entidadId) {
        return transaccionRepository.findByEntidadAfectadaAndEntidadId(entidadAfectada, entidadId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaccion> buscarPorUsuarioResponsable(String usuarioResponsable) {
        return transaccionRepository.findByUsuarioResponsable(usuarioResponsable)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaccion> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return transaccionRepository.findByFechaTransaccionBetween(fechaInicio, fechaFin)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existeTransaccionConDetalles(String tipoOperacion, String entidadAfectada, 
                                                  UUID entidadId, String detalles) {
        return transaccionRepository.existsByTipoOperacionAndEntidadAfectadaAndEntidadIdAndDetalles(
                tipoOperacion, entidadAfectada, entidadId, detalles);
    }

    @Override
    public List<Transaccion> buscarTodos() {
        return transaccionRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private TransaccionEntity toEntity(Transaccion transaccion) {
        TransaccionEntity entity = new TransaccionEntity();
        if (transaccion.getId() != null) {
            entity.setId(transaccion.getId());
        }
        entity.setTipoOperacion(transaccion.getTipoOperacion());
        entity.setEntidadAfectada(transaccion.getEntidadAfectada());
        entity.setEntidadId(transaccion.getEntidadId());
        entity.setDetalles(transaccion.getDetalles());
        entity.setFechaTransaccion(transaccion.getFechaTransaccion());
        entity.setUsuarioResponsable(transaccion.getUsuarioResponsable());
        return entity;
    }

    private Transaccion toDomain(TransaccionEntity entity) {
        return Transaccion.builder()
                .id(entity.getId())
                .tipoOperacion(entity.getTipoOperacion())
                .entidadAfectada(entity.getEntidadAfectada())
                .entidadId(entity.getEntidadId())
                .detalles(entity.getDetalles())
                .fechaTransaccion(entity.getFechaTransaccion())
                .usuarioResponsable(entity.getUsuarioResponsable())
                .build();
    }
}