package com.pragma.ordenes.application.exceptions;

public class InventarioInsuficienteException extends ApplicationException {
    public InventarioInsuficienteException() {
        super("INVENTARIO_INSUFICIENTE",
                "No todos los items solicitados están disponibles en inventario");
    }
}
