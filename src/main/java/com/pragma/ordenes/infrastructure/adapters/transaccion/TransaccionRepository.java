package com.pragma.ordenes.infrastructure.adapters.transaccion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<TransaccionEntity, Long> {
}
