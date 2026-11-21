package com.pragma.ordenes.infrastructure.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class OrdenRequest {
    private String clienteId;
    private String id;
    private List<ItemOrdenRequest>  items;
}
