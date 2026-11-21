package com.pragma.ordenes.infrastructure.adapters.transaccion;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class TransaccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String tipoOperacion;
    private String entidadAfectada;
    private UUID entidadId;
    private String detalles;
    private LocalDateTime fechaTransaccion;
    private String usuarioResponsable;
}
