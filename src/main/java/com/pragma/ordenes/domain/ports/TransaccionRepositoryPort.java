package com.pragma.ordenes.domain.ports;

import com.pragma.ordenes.domain.model.Transaccion;

/**
 * Puerto que define las operaciones de persistencia para el registro de transacciones.
 * Este puerto sigue el principio de inversión de dependencias: el dominio define la interfaz,
 * la infraestructura provee la implementación.
 */
public interface TransaccionRepositoryPort {

    /**
     * Persiste una nueva transacción en el registro de auditoría.
     * @param transaccion La transacción a guardar
     * @return La transacción guardada con su ID asignado
     */
    Transaccion guardar(Transaccion transaccion);
}