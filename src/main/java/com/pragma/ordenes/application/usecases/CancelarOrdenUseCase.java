package com.pragma.ordenes.application.usecases;

import com.pragma.ordenes.application.exceptions.InventarioNoCancelableException;
import com.pragma.ordenes.application.exceptions.OrdenNoEncontradaException;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.ports.InventarioServicePort;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CancelarOrdenUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CancelarOrdenUseCase.class);

    private final OrdenRepositoryPort ordenRepository;
    private final InventarioServicePort inventarioService;
    private final RegistarTransaccionUseCase registarTransaccionUseCase;

    public CancelarOrdenUseCase(OrdenRepositoryPort ordenRepository,
                                InventarioServicePort inventarioService,
                                RegistarTransaccionUseCase registarTransaccionUseCase) {
        this.ordenRepository = ordenRepository;
        this.inventarioService = inventarioService;
        this.registarTransaccionUseCase = registarTransaccionUseCase;
    }

    public Orden cancelarOrden(UUID id) {
        logger.info("Iniciando proceso de cancelación de orden: {}", id);

        Orden orden = getOrden(id);
        persistirOrden(orden);
        actualizarInventario(orden);
        registarTransaccionUseCase.registrarTransaccionOrden(orden);
        registarTransaccionUseCase.registrarTransactionInventario(orden);

        logger.info("Orden {} cancelada exitosamente para cliente: {}",
                orden.getId(), orden.getClienteId());
        return orden;
    }

    private Orden getOrden(UUID idOrden) {
        logger.debug("Validando orden: {}", idOrden);

        if (idOrden == null) {
            throw new IllegalArgumentException("El ID de la orden es obligatorio");
        }

        Orden orden =  ordenRepository.buscarPorId(idOrden).get();

        if (orden == null) {
            throw new OrdenNoEncontradaException(idOrden.toString());
        }

        return orden;
    }

    private Orden persistirOrden(Orden orden) {
        logger.debug("Persistiendo orden para cliente: {}", orden.getClienteId());

        orden.setFechaActualizacion(LocalDateTime.now());
        orden.setEstado(Orden.EstadoOrden.CANCELADA);

        Orden ordenGuardada = ordenRepository.guardar(orden);

        logger.info("Orden {} persistida con estado: {}",
                ordenGuardada.getId(), ordenGuardada.getEstado());
        return ordenGuardada;
    }

    private void actualizarInventario(Orden orden) {
        logger.debug("Actualizando inventario para orden: {}", orden.getId());

        try {
            inventarioService.decrementarStock(orden.getItems());
            logger.info("Inventario actualizado correctamente para orden: {}", orden.getId());
        } catch (Exception e) {
            logger.error("Error al actualizar inventario para orden: {}. Restaurando estado...",
                    orden.getId(), e);
            inventarioService.restaurarStock(orden.getItems());
            throw new InventarioNoCancelableException(e.getMessage());
        }
    }

}