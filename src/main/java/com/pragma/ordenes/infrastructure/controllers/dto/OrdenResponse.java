package com.pragma.ordenes.infrastructure.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class OrdenResponse {

    private String id;
    private String clienteId;
    private List<ItemOrdenResponse> items;
    private String estado;
    private String fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String total;
}
