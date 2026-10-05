package com.pragma.ordenes.domain.ports;

import com.pragma.ordenes.domain.model.Transaccion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    /**
     * Busca una transacción por su identificador único.
     * @param id Identificador de la transacción
     * @return Optional con la transacción si existe
     */
    Optional<Transaccion> buscarPorId(UUID id);

    /**
     * Recupera todas las transacciones asociadas a una entidad específica.
     * @param entidadAfectada Nombre de la entidad (ej. "ORDEN", "INVENTARIO")
     * @param entidadId Identificador de la entidad
     * @return Lista de transacciones relacionadas
     */
    List<Transaccion> buscarPorEntidad(String entidadAfectada, UUID entidadId);

    /**
     * Obtiene transacciones por tipo de operación.
     * @param tipoOperacion Tipo de operación (ej. "CREACION", "ACTUALIZACION", "CANCELACION")
     * @return Lista de transacciones del tipo especificado
     */
    List<Transaccion> buscarPorTipoOperacion(String tipoOperacion);

    /**
     * Recupera transacciones dentro de un rango de fechas.
     * @param fechaInicio Fecha inicial del rango
     * @param fechaFin Fecha final del rango
     * @return Lista de transacciones en el rango especificado
     */
    List<Transaccion> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Busca una transacción por su identificador de correlación para garantizar idempotencia.
     * @param correlationId Identificador de correlación
     * @return Optional con la transacción existente si ya fue procesada
     */
    Optional<Transaccion> buscarPorCorrelationId(String correlationId);

    /**
     * Actualiza los detalles de una transacción existente.
     * @param id Identificador de la transacción
     * @param nuevosDetalles Nuevos detalles a registrar
     * @return La transacción actualizada
     */
    Transaccion actualizarDetalles(UUID id, String nuevosDetalles);

    /**
     * Cuenta el número total de transacciones en el sistema.
     * @return Cantidad total de transacciones
     */
    long contarTransacciones();
}