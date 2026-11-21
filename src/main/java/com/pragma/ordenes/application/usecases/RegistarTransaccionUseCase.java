package com.pragma.ordenes.application.usecases;

import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.model.Transaccion;
import com.pragma.ordenes.domain.ports.TransaccionRepositoryPort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistarTransaccionUseCase {

    private static final Logger logger = LoggerFactory.getLogger(RegistarTransaccionUseCase.class);

    private final TransaccionRepositoryPort transaccionRepository;

    public void registrarTransaccionOrden(Orden orden) {
        logger.debug("Registrando transacción de auditoría para orden: {}", orden.getId());

        Transaccion transaccionOrden = Transaccion.crearTransaccion(
                "CREACION",
                "ORDEN",
                orden.getId(),
                String.format("Orden creada para cliente %s con %d items, total: %s",
                        orden.getClienteId(),
                        orden.getItems().size(),
                        orden.calcularTotal())
        );

        transaccionRepository.guardar(transaccionOrden);

        logger.info("Transacciones de auditoría registradas para orden: {}", orden.getId());
    }

    public void registrarTransactionInventario(Orden orden) {
        for (Orden.ItemOrden item : orden.getItems()) {
            Transaccion transaccionInventario = Transaccion.crearTransaccion(
                    "DECREMENTO_STOCK",
                    "INVENTARIO",
                    item.getProductoId(),
                    String.format("Stock decrementado en %d unidades para orden %s",
                            item.getCantidad(),
                            orden.getId())
            );

            transaccionRepository.guardar(transaccionInventario);
        }
    }
}
