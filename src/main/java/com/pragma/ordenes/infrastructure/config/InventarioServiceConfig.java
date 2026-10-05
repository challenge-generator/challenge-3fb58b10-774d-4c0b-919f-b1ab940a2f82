package com.pragma.ordenes.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConfigurationProperties(prefix = "app.inventario")
@Getter
@Setter
@Slf4j
public class InventarioServiceConfig {
    private String baseUrl;
    private int connectTimeout = 5000;
    private int readTimeout = 10000;
    private int maxRetries = 3;
    private long retryDelay = 1000L;
    private boolean enabled = true;

    @Bean
    public RestTemplate inventarioRestTemplate() {
        log.info("Configurando RestTemplate para servicio de inventario: baseUrl={}, timeouts=[{}ms, {}ms]",
                baseUrl, connectTimeout, readTimeout);
        
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        
        RestTemplate restTemplate = new RestTemplate(factory);
        
        return restTemplate;
    }

    public String getUrlBase() {
        return baseUrl != null ? baseUrl.trim() : null;
    }

    public String getEndpointVerificacionStock() {
        return getUrlBase() + "/api/v1/inventario/verificar-stock";
    }

    public String getEndpointReservarStock() {
        return getUrlBase() + "/api/v1/inventario/reservar";
    }

    public String getEndpointConfirmarReserva() {
        return getUrlBase() + "/api/v1/inventario/confirmar-reserva";
    }

    public String getEndpointCancelarReserva() {
        return getUrlBase() + "/api/v1/inventario/cancelar-reserva";
    }

    public boolean isIntegracionHabilitada() {
        return enabled && baseUrl != null && !baseUrl.isBlank();
    }

    public void validarConfiguracion() {
        if (!enabled) {
            log.warn("Integración con servicio de inventario deshabilitada");
            return;
        }
        
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("La URL base del servicio de inventario no está configurada");
        }
        
        if (connectTimeout <= 0) {
            throw new IllegalStateException("El timeout de conexión debe ser mayor a 0");
        }
        
        if (readTimeout <= 0) {
            throw new IllegalStateException("El timeout de lectura debe ser mayor a 0");
        }
        
        if (readTimeout > 2000) {
            log.warn("El timeout de lectura excede el máximo recomendado de 2000ms. Valor actual: {}ms", readTimeout);
        }
        
        log.info("Configuración del servicio de inventario validada correctamente");
    }
}