package com.pragma.ordenes.domain.ports;


import com.pragma.ordenes.domain.model.EstadoOrden;
import com.pragma.ordenes.domain.model.Orden;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interfaz de puerto que define las operaciones de persistencia para órdenes.
 * Es implementada por la capa de infraestructura pero definida por el dominio.
 */
public interface OrdenRepositoryPort {
    /**
     * Guarda una orden en el repositorio.
     * @param orden Orden a guardar
     * @return Orden guardada
     */
    Orden guardar(Orden orden);

    /**
     * Busca una orden por su ID.
     * @param id ID de la orden
     * @return Optional con la orden si existe
     */
    Optional<Orden> buscarPorId(UUID id);

    /**
     * Busca todas las órdenes de un cliente específico.
     * @param clienteId ID del cliente
     * @return Lista de órdenes del cliente
     */
    List<Orden> buscarPorClienteId(String clienteId);

    /**
     * Busca todas las órdenes con un estado específico.
     * @param estado Estado de la orden
     * @return Lista de órdenes con el estado dado
     */
    List<Orden> buscarPorEstado(Orden.EstadoOrden estado);

    /**
     * Actualiza el estado de una orden existente.
     * @param id ID de la orden
     * @param nuevoEstado Nuevo estado
     * @return Orden actualizada
     */
    Orden actualizarEstado(UUID id, Orden.EstadoOrden nuevoEstado);
}