package com.pragma.ordenes.infrastructure.adapters.transaccion;

import com.pragma.ordenes.domain.model.Transaccion;

public class TransaccionMapper {

    public TransaccionEntity toEntity(Transaccion transaccion) {
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

    public Transaccion toDomain(TransaccionEntity entity) {
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
