package com.pragma.ordenes.infrastructure.adapters.order;

import com.pragma.ordenes.domain.model.Orden;

import java.util.List;

public class OrdenMapper {

    public Orden toDomain(OrdenEntity entity) {
        if (entity == null) {
            return null;
        }

        Orden orden = new Orden();
        orden.setId(entity.getId());
        orden.setClienteId(entity.getClienteId());
        orden.setItems(mapItemsToDomain(entity.getItems()));
        orden.setEstado(entity.getEstado());
        orden.setFechaCreacion(entity.getFechaCreacion());
        orden.setFechaActualizacion(entity.getFechaActualizacion());

        return orden;
    }

    public OrdenEntity toEntity(Orden orden) {
        if (orden == null) {
            return null;
        }

        OrdenEntity entity = new OrdenEntity();
        entity.setId(orden.getId());
        entity.setClienteId(orden.getClienteId());
        entity.setItems(mapItemsToEntity(orden.getItems()));
        entity.setEstado(orden.getEstado());
        entity.setFechaCreacion(orden.getFechaCreacion());
        entity.setFechaActualizacion(orden.getFechaActualizacion());

        return entity;
    }

    private List<Orden.ItemOrden> mapItemsToDomain(List<ItemOrdenEntity> items) {
        if (items == null) {
            return null;
        }

        return items.stream()
                .map(this::mapItemToDomain)
                .toList();
    }

    private List<ItemOrdenEntity> mapItemsToEntity(List<Orden.ItemOrden> items) {
        if (items == null) {
            return null;
        }

        return items.stream()
                .map(this::mapItemToEntity)
                .toList();
    }

    private Orden.ItemOrden mapItemToDomain(ItemOrdenEntity entity) {
        if (entity == null) {
            return null;
        }

        Orden.ItemOrden item = new Orden.ItemOrden();
        item.setProductoId(entity.getProductoId());
        item.setNombreProducto(entity.getNombreProducto());
        item.setCantidad(entity.getCantidad());
        item.setPrecioUnitario(entity.getPrecioUnitario());

        return item;
    }

    private ItemOrdenEntity mapItemToEntity(Orden.ItemOrden item) {
        if (item == null) {
            return null;
        }

        ItemOrdenEntity entity = new ItemOrdenEntity();
        entity.setProductoId(item.getProductoId());
        entity.setNombreProducto(item.getNombreProducto());
        entity.setCantidad(item.getCantidad());
        entity.setPrecioUnitario(item.getPrecioUnitario());

        return entity;
    }
}
