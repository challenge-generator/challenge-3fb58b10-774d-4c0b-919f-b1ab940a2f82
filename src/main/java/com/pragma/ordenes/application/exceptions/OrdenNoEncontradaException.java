package com.pragma.ordenes.application.exceptions;

public class OrdenNoEncontradaException extends RuntimeException {

    public OrdenNoEncontradaException(String message) {
        super("Orden no encontrada: " + message);
    }
}
