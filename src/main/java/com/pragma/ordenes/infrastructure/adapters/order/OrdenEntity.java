package com.pragma.ordenes.infrastructure.adapters.order;

import com.pragma.ordenes.domain.model.Orden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrdenEntity {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String clienteId;

    @OneToMany(
            mappedBy = "orden",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemOrdenEntity> items;

    private Orden.EstadoOrden estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
}
