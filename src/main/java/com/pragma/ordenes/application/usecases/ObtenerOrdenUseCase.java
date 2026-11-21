package com.pragma.ordenes.application.usecases;

import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ObtenerOrdenUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CrearOrdenUseCase.class);
    private OrdenRepositoryPort ordenRepositoryPort;

    public Orden buscarPorId(UUID idOrden) {
        logger.debug("Buscando Orden por id {}", idOrden);

        if (idOrden == null) {
            throw new IllegalArgumentException("El ID de la orden es obligatorio");
        }
        return ordenRepositoryPort.buscarPorId(idOrden)
                .get();
    }

    public List<Orden> buscarPorClienteId(String entidadAfectada) {
        logger.debug("Buscando Órdenes por entidad afectada {}", entidadAfectada);

        if (entidadAfectada == null) {
            throw new IllegalArgumentException("La entidad afectada es obligatoria");
        }

        return ordenRepositoryPort.buscarPorClienteId(entidadAfectada);
    }

    public List<Orden> buscarPorEstado(Orden.EstadoOrden estado) {
        logger.debug("Buscando Órdenes por estado {}", estado);

        if (estado == null) {
            throw new IllegalArgumentException("El estado de la orden es obligatorio");
        }

        return ordenRepositoryPort.buscarPorEstado(estado);
    }
}
