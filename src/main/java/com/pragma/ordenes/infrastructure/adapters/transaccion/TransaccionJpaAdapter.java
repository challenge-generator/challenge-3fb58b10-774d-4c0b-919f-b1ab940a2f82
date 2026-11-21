package com.pragma.ordenes.infrastructure.adapters.transaccion;

import com.pragma.ordenes.domain.model.Transaccion;
import com.pragma.ordenes.domain.ports.TransaccionRepositoryPort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class TransaccionJpaAdapter implements TransaccionRepositoryPort {

    private final TransaccionRepository transaccionRepository;
    private final TransaccionMapper  transaccionMapper;

    public TransaccionJpaAdapter(TransaccionRepository transaccionRepository, TransaccionMapper  transaccionMapper) {
        this.transaccionRepository = transaccionRepository;
        this.transaccionMapper = transaccionMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Transaccion guardar(Transaccion transaccion) {
        if (transaccion.getId() == null) {
            transaccion.setId(UUID.randomUUID());
        }
        if (transaccion.getFechaTransaccion() == null) {
            transaccion.setFechaTransaccion(LocalDateTime.now());
        }
        TransaccionEntity entity = transaccionMapper.toEntity(transaccion);
        TransaccionEntity savedEntity = transaccionRepository.save(entity);
        return transaccionMapper.toDomain(savedEntity);
    }
}