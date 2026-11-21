package com.pragma.ordenes.infrastructure.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
public class ItemOrdenResponse {
    private UUID productoId;
    private String nombreProducto;
    private Integer cantidad;
    private String precioUnitario;
    private String subtotal;
}
