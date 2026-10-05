package com.pragma.ordenes.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad de dominio que representa una transacción para auditoría.
 * Registra todas las operaciones relevantes del sistema para trazabilidad.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {
    @NotNull(message = "El ID de la transacción no puede ser nulo")
    private UUID id;

    @NotBlank(message = "El tipo de operación no puede estar vacío")
    @Size(max = 50, message = "El tipo de operación no puede exceder 50 caracteres")
    private String tipoOperacion;

    @NotBlank(message = "La entidad afectada no puede estar vacía")
    @Size(max = 50, message = "La entidad afectada no puede exceder 50 caracteres")
    private String entidadAfectada;

    @NotNull(message = "El ID de la entidad no puede ser nulo")
    private UUID entidadId;

    @NotBlank(message = "Los detalles no pueden estar vacíos")
    @Size(max = 500, message = "Los detalles no pueden exceder 500 caracteres")
    private String detalles;

    @NotNull(message = "La fecha de la transacción no puede ser nula")
    private LocalDateTime fechaTransaccion;

    @Size(max = 100, message = "El usuario responsable no puede exceder 100 caracteres")
    private String usuarioResponsable;

    /**
     * Actualiza los detalles de la transacción.
     * @param nuevosDetalles Nuevos detalles a registrar
     */
    public void actualizarDetalles(String nuevosDetalles) {
        this.detalles = nuevosDetalles;
        this.fechaTransaccion = LocalDateTime.now();
    }

    /**
     * Crea una nueva transacción para registrar una operación.
     * @param tipoOperacion Tipo de operación realizada
     * @param entidadAfectada Entidad que fue modificada
     * @param entidadId ID de la entidad modificada
     * @param detalles Detalles adicionales de la operación
     * @return Nueva instancia de Transaccion
     */
    public static Transaccion crearTransaccion(String tipoOperacion, String entidadAfectada,
            UUID entidadId, String detalles) {
        return Transaccion.builder()
                .id(UUID.randomUUID())
                .tipoOperacion(tipoOperacion)
                .entidadAfectada(entidadAfectada)
                .entidadId(entidadId)
                .detalles(detalles)
                .fechaTransaccion(LocalDateTime.now())
                .usuarioResponsable("sistema")
                .build();
    }
}