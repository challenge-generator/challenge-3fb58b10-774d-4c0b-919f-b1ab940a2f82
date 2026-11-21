package com.pragma.ordenes.application.usecases;

import com.pragma.ordenes.application.exceptions.InventarioInsuficienteException;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.ports.InventarioServicePort;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import com.pragma.ordenes.infrastructure.exceptions.InventarioServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CrearOrdenUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CrearOrdenUseCase.class);

    private final OrdenRepositoryPort ordenRepository;
    private final InventarioServicePort inventarioService;
    private final RegistarTransaccionUseCase  registarTransaccionUseCase;

    public CrearOrdenUseCase(
            OrdenRepositoryPort ordenRepository,
            InventarioServicePort inventarioService,
            RegistarTransaccionUseCase registarTransaccionUseCase) {
        this.ordenRepository = ordenRepository;
        this.inventarioService = inventarioService;
        this.registarTransaccionUseCase = registarTransaccionUseCase;
    }

    public Orden ejecutar(Orden orden) {
        logger.info("Iniciando proceso de creación de orden para cliente: {}", orden.getClienteId());

        validarOrden(orden);
        verificarDisponibilidadInventario(orden);
        Orden ordenGuardada = persistirOrden(orden);
        actualizarInventario(ordenGuardada);
        registarTransaccionUseCase.registrarTransaccionOrden(orden);
        registarTransaccionUseCase.registrarTransactionInventario(orden);

        logger.info("Orden {} creada exitosamente para cliente: {}",
                ordenGuardada.getId(), ordenGuardada.getClienteId());
        return ordenGuardada;
    }

    private void validarOrden(Orden orden) {
        logger.debug("Validando orden para cliente: {}", orden.getClienteId());

        if (orden.getClienteId() == null || orden.getClienteId().isBlank()) {
            throw new IllegalArgumentException("El ID del cliente es obligatorio");
        }

        if (orden.getItems() == null || orden.getItems().isEmpty()) {
            throw new IllegalArgumentException("La orden debe contener al menos un item");
        }

        orden.validarItems();

        if (orden.calcularTotal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El total de la orden debe ser mayor a cero");
        }

        logger.debug("Orden validada correctamente. Total: {}", orden.calcularTotal());
    }

    private void verificarDisponibilidadInventario(Orden orden) {
        logger.debug("Verificando disponibilidad en inventario para {} items", orden.getItems().size());

        boolean disponible = inventarioService.verificarDisponibilidad(orden.getItems());

        if (!disponible) {
            logger.error("No hay disponibilidad en inventario para la orden del cliente: {}",
                    orden.getClienteId());
            throw new InventarioInsuficienteException();
        }

        logger.debug("Inventario verificado: todos los items disponibles");
    }

    private Orden persistirOrden(Orden orden) {
        logger.debug("Persistiendo orden para cliente: {}", orden.getClienteId());

        orden.setId(UUID.randomUUID());
        orden.setFechaCreacion(LocalDateTime.now());
        orden.setFechaActualizacion(LocalDateTime.now());
        orden.setEstado(Orden.EstadoOrden.PENDIENTE);

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
            throw new InventarioServiceException(
                    "Error al actualizar el inventario: " + e.getMessage(), e);
        }
    }
}