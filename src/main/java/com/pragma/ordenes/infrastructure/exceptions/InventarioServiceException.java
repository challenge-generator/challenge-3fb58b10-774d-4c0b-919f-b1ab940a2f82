package com.pragma.ordenes.infrastructure.exceptions;

public class InventarioServiceException extends RuntimeException {
    private final String codigoError;
    private final String detalleTecnico;

    public InventarioServiceException(String mensaje) {
        super(mensaje);
        this.codigoError = "INV_ERROR_GENERICO";
        this.detalleTecnico = null;
    }

    public InventarioServiceException(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
        this.detalleTecnico = null;
    }

    public InventarioServiceException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = "INV_ERROR_GENERICO";
        this.detalleTecnico = causa != null ? causa.getMessage() : null;
    }

    public InventarioServiceException(String mensaje, String codigoError, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.detalleTecnico = causa != null ? causa.getMessage() : null;
    }

    public InventarioServiceException(String mensaje, String codigoError, String detalleTecnico, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.detalleTecnico = detalleTecnico;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public String getDetalleTecnico() {
        return detalleTecnico;
    }

    public boolean isTemporal() {
        return "INV_ERROR_TIMEOUT".equals(codigoError) || 
               "INV_ERROR_CONEXION".equals(codigoError) ||
               "INV_ERROR_SERVICIO_NO_DISPONIBLE".equals(codigoError);
    }

    public static InventarioServiceException timeout(String mensaje) {
        return new InventarioServiceException(mensaje, "INV_ERROR_TIMEOUT");
    }

    public static InventarioServiceException conexion(String mensaje, Throwable causa) {
        return new InventarioServiceException(mensaje, "INV_ERROR_CONEXION", causa);
    }

    public static InventarioServiceException servicioNoDisponible(String mensaje) {
        return new InventarioServiceException(mensaje, "INV_ERROR_SERVICIO_NO_DISPONIBLE");
    }

    public static InventarioServiceException respuestaInvalida(String mensaje, Throwable causa) {
        return new InventarioServiceException(mensaje, "INV_ERROR_RESPUESTA_INVALIDA", causa);
    }
}