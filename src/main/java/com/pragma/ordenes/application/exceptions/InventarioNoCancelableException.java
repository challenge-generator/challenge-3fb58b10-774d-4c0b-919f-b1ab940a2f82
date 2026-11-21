package com.pragma.ordenes.application.exceptions;

public class InventarioNoCancelableException extends ApplicationException{
    public InventarioNoCancelableException(String message) {
        super("INVENTARIO_NO_CANCELABLE",
                "Error al actualizar el inventario: " + message);
    }
}
