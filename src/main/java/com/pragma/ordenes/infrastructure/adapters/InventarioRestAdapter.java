package com.pragma.ordenes.infrastructure.adapters;

import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.ports.InventarioServicePort;
import com.pragma.ordenes.infrastructure.config.InventarioServiceConfig;
import com.pragma.ordenes.infrastructure.exceptions.InventarioServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InventarioRestAdapter implements InventarioServicePort {

    private static final Logger log = LoggerFactory.getLogger(InventarioRestAdapter.class);
    private static final int TIMEOUT_SECONDS = 2;

    private final RestTemplate restTemplate;
    private final InventarioServiceConfig config;

    public InventarioRestAdapter(RestTemplate restTemplate, InventarioServiceConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    @Override
    @CircuitBreaker(name = "inventarioCircuitBreaker", fallbackMethod = "reservarInventarioFallback")
    @Retry(name = "inventarioRetry")
    public boolean reservarInventario(Orden orden) {
        log.info("Iniciando reserva de inventario para orden: {}", orden.getId());
        
        validarOrden(orden);
        
        Map<String, Object> requestBody = construirRequestBody(orden);
        HttpEntity<Map<String, Object>> request = construirRequest(requestBody);
        
        String url = config.getBaseUrl() + "/api/inventario/reservar";
        log.debug("Llamando a servicio de inventario: {}", url);
        
        try {
            Map<String, Object> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    Map.class
            ).getBody();
            
            boolean success = response != null && Boolean.TRUE.equals(response.get("success"));
            log.info("Reserva de inventario completada para orden {}: {}", orden.getId(), success);
            return success;
            
        } catch (Exception e) {
            log.error("Error al reservar inventario para orden {}: {}", orden.getId(), e.getMessage());
            throw new InventarioServiceException("Error al reservar inventario: " + e.getMessage(), e);
        }
    }

    @Override
    @CircuitBreaker(name = "inventarioCircuitBreaker", fallbackMethod = "confirmarReservaFallback")
    @Retry(name = "inventarioRetry")
    public boolean confirmarReserva(String ordenId) {
        log.info("Confirmando reserva de inventario para orden: {}", ordenId);
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("ordenId", ordenId);
        HttpEntity<Map<String, Object>> request = construirRequest(requestBody);
        
        String url = config.getBaseUrl() + "/api/inventario/confirmar";
        
        try {
            Map<String, Object> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    Map.class
            ).getBody();
            
            boolean success = response != null && Boolean.TRUE.equals(response.get("success"));
            log.info("Confirmación de reserva completada para orden {}: {}", ordenId, success);
            return success;
            
        } catch (Exception e) {
            log.error("Error al confirmar reserva para orden {}: {}", ordenId, e.getMessage());
            throw new InventarioServiceException("Error al confirmar reserva: " + e.getMessage(), e);
        }
    }

    @Override
    @CircuitBreaker(name = "inventarioCircuitBreaker", fallbackMethod = "cancelarReservaFallback")
    @Retry(name = "inventarioRetry")
    public boolean cancelarReserva(String ordenId) {
        log.info("Cancelando reserva de inventario para orden: {}", ordenId);
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("ordenId", ordenId);
        HttpEntity<Map<String, Object>> request = construirRequest(requestBody);
        
        String url = config.getBaseUrl() + "/api/inventario/cancelar";
        
        try {
            Map<String, Object> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    Map.class
            ).getBody();
            
            boolean success = response != null && Boolean.TRUE.equals(response.get("success"));
            log.info("Cancelación de reserva completada para orden {}: {}", ordenId, success);
            return success;
            
        } catch (Exception e) {
            log.error("Error al cancelar reserva para orden {}: {}", ordenId, e.getMessage());
            throw new InventarioServiceException("Error al cancelar reserva: " + e.getMessage(), e);
        }
    }

    private void validarOrden(Orden orden) {
        if (orden == null) {
            throw new InventarioServiceException("La orden no puede ser nula");
        }
        if (orden.getId() == null) {
            throw new InventarioServiceException("El ID de la orden no puede ser nulo");
        }
        if (orden.getItems() == null || orden.getItems().isEmpty()) {
            throw new InventarioServiceException("La orden debe tener al menos un item");
        }
    }

    private Map<String, Object> construirRequestBody(Orden orden) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("ordenId", orden.getId().toString());
        requestBody.put("clienteId", orden.getClienteId());
        
        List<Map<String, Object>> items = orden.getItems().stream()
                .map(item -> {
                    Map<String, Object> itemMap = new HashMap<>();
                    itemMap.put("productoId", item.getProductoId());
                    itemMap.put("cantidad", item.getCantidad());
                    return itemMap;
                })
                .collect(Collectors.toList());
        
        requestBody.put("items", items);
        return requestBody;
    }

    private HttpEntity<Map<String, Object>> construirRequest(Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Service-Name", "ordenes-service");
        return new HttpEntity<>(body, headers);
    }

    private boolean reservarInventarioFallback(Orden orden, Exception e) {
        log.warn("FALLBACK: Reservar inventario para orden {} - Error: {}", orden.getId(), e.getMessage());
        return false;
    }

    private boolean confirmarReservaFallback(String ordenId, Exception e) {
        log.warn("FALLBACK: Confirmar reserva para orden {} - Error: {}", ordenId, e.getMessage());
        return false;
    }

    private boolean cancelarReservaFallback(String ordenId, Exception e) {
        log.warn("FALLBACK: Cancelar reserva para orden {} - Error: {}", ordenId, e.getMessage());
        return false;
    }
}