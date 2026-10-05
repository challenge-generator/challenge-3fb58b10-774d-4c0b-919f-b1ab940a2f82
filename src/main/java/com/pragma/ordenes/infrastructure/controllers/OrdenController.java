package com.pragma.ordenes.infrastructure.controllers;

import com.pragma.ordenes.application.usecases.CrearOrdenUseCase;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.model.Orden.EstadoOrden;
import com.pragma.ordenes.domain.model.Orden.ItemOrden;
import com.pragma.ordenes.infrastructure.adapters.dto.OrdenRequest;
import com.pragma.ordenes.infrastructure.adapters.dto.OrdenResponse;
import com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenRequest;
import com.pragma.ordenes.infrastructure.adapters.dto.ItemOrdenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ordenes")
@Tag(name = "Órdenes", description = "API para la gestión de órdenes de compra")
public class OrdenController {

    private final CrearOrdenUseCase crearOrdenUseCase;

    public OrdenController(CrearOrdenUseCase crearOrdenUseCase) {
        this.crearOrdenUseCase = crearOrdenUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva orden", description = "Crea una nueva orden de compra y reserva el inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orden creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "409", description = "Conflicto de inventario"),
            @ApiResponse(responseCode = "503", description = "Servicio de inventario no disponible")
    })
    public ResponseEntity<OrdenResponse> crearOrden(@Valid @RequestBody OrdenRequest request) {
        Orden orden = mapRequestToDomain(request);
        Orden ordenCreada = crearOrdenUseCase.ejecutar(orden);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapDomainToResponse(ordenCreada));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener orden por ID", description = "Retorna los detalles de una orden específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden encontrada"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    public ResponseEntity<OrdenResponse> obtenerOrdenPorId(
            @Parameter(description = "ID de la orden", required = true) @PathVariable UUID id) {
        Orden orden = crearOrdenUseCase.buscarPorId(id);
        if (orden == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapDomainToResponse(orden));
    }

    @GetMapping
    @Operation(summary = "Listar órdenes por cliente", description = "Retorna todas las órdenes de un cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de órdenes retornada")
    })
    public ResponseEntity<List<OrdenResponse>> listarOrdenesPorCliente(
            @Parameter(description = "ID del cliente", required = true) @RequestParam String clienteId) {
        List<Orden> ordenes = crearOrdenUseCase.buscarPorClienteId(clienteId);
        List<OrdenResponse> responses = ordenes.stream()
                .map(this::mapDomainToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Listar órdenes por estado", description = "Retorna todas las órdenes con un estado específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de órdenes retornada")
    })
    public ResponseEntity<List<OrdenResponse>> listarOrdenesPorEstado(
            @Parameter(description = "Estado de la orden", required = true) @PathVariable EstadoOrden estado) {
        List<Orden> ordenes = crearOrdenUseCase.buscarPorEstado(estado);
        List<OrdenResponse> responses = ordenes.stream()
                .map(this::mapDomainToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar orden", description = "Cancela una orden existente liberando el inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden cancelada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada"),
            @ApiResponse(responseCode = "409", description = "La orden no puede ser cancelada en su estado actual")
    })
    public ResponseEntity<OrdenResponse> cancelarOrden(
            @Parameter(description = "ID de la orden", required = true) @PathVariable UUID id) {
        Orden ordenCancelada = crearOrdenUseCase.cancelarOrden(id);
        if (ordenCancelada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapDomainToResponse(ordenCancelada));
    }

    private Orden mapRequestToDomain(OrdenRequest request) {
        List<ItemOrden> items = request.getItems().stream()
                .map(this::mapItemRequestToDomain)
                .collect(Collectors.toList());
        
        return Orden.builder()
                .id(request.getId() != null ? UUID.fromString(request.getId()) : null)
                .clienteId(request.getClienteId())
                .items(items)
                .estado(EstadoOrden.PENDIENTE)
                .build();
    }

    private ItemOrden mapItemRequestToDomain(ItemOrdenRequest request) {
        return ItemOrden.builder()
                .productoId(request.getProductoId())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .build();
    }

    private OrdenResponse mapDomainToResponse(Orden orden) {
        List<ItemOrdenResponse> items = orden.getItems().stream()
                .map(this::mapItemDomainToResponse)
                .collect(Collectors.toList());
        
        return OrdenResponse.builder()
                .id(orden.getId().toString())
                .clienteId(orden.getClienteId())
                .items(items)
                .estado(orden.getEstado().name())
                .total(orden.calcularTotal().toString())
                .fechaCreacion(orden.getFechaCreacion() != null ? orden.getFechaCreacion().toString() : null)
                .build();
    }

    private ItemOrdenResponse mapItemDomainToResponse(ItemOrden item) {
        return ItemOrdenResponse.builder()
                .productoId(item.getProductoId())
                .cantidad(item.getCantidad())
                .precioUnitario(item.getPrecioUnitario().toString())
                .subtotal(item.getSubtotal().toString())
                .build();
    }
}