package com.pragma.ordenes.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Entidad de dominio que representa una orden de compra.
 * Contiene la lógica de negocio relacionada con la creación y validación de órdenes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orden {
    @NotNull(message = "El ID de la orden no puede ser nulo")
    private UUID id;

    @NotBlank(message = "El ID del cliente no puede estar vacío")
    @Size(max = 50, message = "El ID del cliente no puede exceder 50 caracteres")
    private String clienteId;

    @NotNull(message = "La lista de items no puede ser nula")
    @Size(min = 1, message = "La orden debe contener al menos un item")
    private List<ItemOrden> items;

    @NotNull(message = "El estado de la orden no puede ser nulo")
    private EstadoOrden estado;

    @NotNull(message = "La fecha de creación no puede ser nula")
    private LocalDateTime fechaCreacion;

    @NotNull(message = "La fecha de actualización no puede ser nula")
    private LocalDateTime fechaActualizacion;

    /**
     * Calcula el total de la orden sumando el precio de todos los items.
     * @return Total de la orden como BigDecimal
     */
    public BigDecimal calcularTotal() {
        return items.stream()
                .map(ItemOrden::getPrecioTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Valida que la orden tenga items con cantidades positivas.
     * @throws IllegalArgumentException si algún item tiene cantidad no positiva
     */
    public void validarItems() {
        if (items.stream().anyMatch(item -> item.getCantidad() <= 0)) {
            throw new IllegalArgumentException("Todos los items deben tener cantidad positiva");
        }
    }

    /**
     * Actualiza el estado de la orden y registra la fecha de actualización.
     * @param nuevoEstado Estado al que se actualizará la orden
     */
    public void actualizarEstado(EstadoOrden nuevoEstado) {
        this.estado = nuevoEstado;
        this.fechaActualizacion = LocalDateTime.now();
    }

    /**
     * Representa un item dentro de una orden.
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ItemOrden {
        @NotNull(message = "El ID del producto no puede ser nulo")
        private UUID productoId;

        @NotBlank(message = "El nombre del producto no puede estar vacío")
        @Size(max = 100, message = "El nombre del producto no puede exceder 100 caracteres")
        private String nombreProducto;

        @NotNull(message = "La cantidad no puede ser nula")
        @Positive(message = "La cantidad debe ser positiva")
        private Integer cantidad;

        @NotNull(message = "El precio unitario no puede ser nulo")
        @Positive(message = "El precio unitario debe ser positivo")
        private BigDecimal precioUnitario;

        /**
         * Calcula el precio total del item.
         * @return Precio total como BigDecimal
         */
        public BigDecimal getPrecioTotal() {
            return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
        }
    }

    /**
     * Enumeración de estados posibles para una orden.
     */
    public enum EstadoOrden {
        PENDIENTE,
        PROCESANDO,
        COMPLETADA,
        CANCELADA,
        FALLIDA
    }
}