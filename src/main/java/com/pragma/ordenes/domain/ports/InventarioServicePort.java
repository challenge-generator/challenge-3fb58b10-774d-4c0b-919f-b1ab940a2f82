package com.pragma.ordenes.domain.ports;


import com.pragma.ordenes.domain.model.ItemOrden;
import com.pragma.ordenes.domain.model.Orden;
import java.util.List;
import java.util.UUID;

/**
 * Puerto que define la interfaz para interactuar con el servicio externo de inventario.
 * Este puerto es implementado por la capa de infraestructura (adaptadores)
 * y consumido por los casos de uso de la capa de aplicación.
 */
public interface InventarioServicePort {

    /**
     * Verifica la disponibilidad de todos los items de una orden en el inventario.
     * @param items Lista de items de la orden con productoId y cantidad
     * @return true si todos los items están disponibles, false en caso contrario
     */
    boolean verificarDisponibilidad(List<Orden.ItemOrden> items);

    /**
     * Decrementa el stock de los items en el inventario após la confirmación de la orden.
     * @param items Lista de items a decrementar
     * @throws Exception si ocurre un error al actualizar el inventario
     */
    void decrementarStock(List<Orden.ItemOrden> items) throws Exception;

    /**
     * Restaura el stock de los items en caso de fallo en el procesamiento de la orden.
     * @param items Lista de items a restaurar
     */
    void restaurarStock(List<Orden.ItemOrden> items);

    /**
     * Obtiene el precio de un producto específico del inventario externo.
     * @param productoId Identificador del producto
     * @return Precio del producto
     */
    java.math.BigDecimal obtenerPrecioProducto(UUID productoId);

    /**
     * Valida que un producto exista en el inventario.
     * @param productoId Identificador del producto
     * @return true si el producto existe, false en caso contrario
     */
    boolean validarProductoExistente(UUID productoId);
}