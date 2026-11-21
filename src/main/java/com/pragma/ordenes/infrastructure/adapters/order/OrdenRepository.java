package com.pragma.ordenes.infrastructure.adapters.order;

import com.pragma.ordenes.domain.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface OrdenRepository extends JpaRepository<OrdenEntity, Long> {

    Optional<OrdenEntity> findById(UUID id);
    OrdenEntity save(OrdenEntity entity);
    Collection<OrdenEntity> findByClienteId(String clienteId);
    Collection<OrdenEntity> findByEstado(Orden.EstadoOrden estado);
}
