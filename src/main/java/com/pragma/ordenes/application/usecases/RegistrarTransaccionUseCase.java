package com.pragma.ordenes.application.usecases;

import com.pragma.ordenes.domain.model.Transaccion;
import com.pragma.ordenes.domain.ports.TransaccionRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrarTransaccionUseCase {

    private static final Logger log = LoggerFactory.getLogger(RegistrarTransaccionUseCase.class);

    private final TransaccionRepositoryPort transaccionRepository;

    public RegistrarTransaccionUseCase(TransaccionRepositoryPort transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    @Transactional
    public Transaccion ejecutar(String tipoOperacion, String entidadAfectada, UUID entidadId, 
                                 String usuarioResponsable, String detalles) {
        log.info("Iniciando registro de transacción: tipo={}, entidad={}, entidadId={}",
                tipoOperacion, entidadAfectada, entidadId);

        Transaccion transaccion = Transaccion.crearTransaccion(
                tipoOperacion,
                entidadAfectada,
                entidadId,
                usuarioResponsable,
                detalles
        );

        Transaccion guardada = transaccionRepository.guardar(transaccion);
        log.info("Transacción registrada exitosamente con ID: {}", guardada.getId());

        return guardada;
    }

    @Transactional(readOnly = true)
    public Optional<Transaccion> buscarPorId(UUID id) {
        log.debug("Buscando transacción por ID: {}", id);
        return transaccionRepository.buscarPorId(id);
    }

    @Transactional(readOnly = true)
    public java.util.List<Transaccion> buscarPorEntidadAfectada(String entidadAfectada) {
        log.debug("Buscando transacciones para entidad: {}", entidadAfectada);
        return transaccionRepository.buscarPorEntidadAfectada(entidadAfectada);
    }

    @Transactional(readOnly = true)
    public java.util.List<Transaccion> buscarPorEntidadId(UUID entidadId) {
        log.debug("Buscando transacciones para entidadId: {}", entidadId);
        return transaccionRepository.buscarPorEntidadId(entidadId);
    }

    @Transactional
    public Transaccion actualizarDetalles(UUID id, String nuevosDetalles) {
        log.info("Actualizando detalles de transacción: {}", id);
        Optional<Transaccion> transaccionOpt = transaccionRepository.buscarPorId(id);
        
        if (transaccionOpt.isEmpty()) {
            log.error("Transacción no encontrada: {}", id);
            throw new IllegalArgumentException("Transacción no encontrada con ID: " + id);
        }
        
        Transaccion transaccion = transaccionOpt.get();
        transaccion.actualizarDetalles(nuevosDetalles);
        return transaccionRepository.guardar(transaccion);
    }
}