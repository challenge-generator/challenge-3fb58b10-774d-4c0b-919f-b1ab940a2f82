package com.pragma.ordenes.infrastructure.adapters;

import com.pragma.ordenes.infrastructure.config.InventarioServiceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests de integración para InventarioRestAdapter")
class InventarioRestAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private InventarioServiceConfig config;

    private InventarioRestAdapter inventarioRestAdapter;

    private static final String INVENTARIO_BASE_URL = "http://localhost:8081/api/inventario";

    @BeforeEach
    void setUp() {
        when(config.getBaseUrl()).thenReturn(INVENTARIO_BASE_URL);
        when(config.getTimeout()).thenReturn(2000);
        inventarioRestAdapter = new InventarioRestAdapter(restTemplate, config);
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe retornar true cuando la reserva de inventario es exitosa")
    void debeRetornarTrue_CuandoReservaExitosa() {
        // Given
        Map<String, Object> request = crearRequestReserva();
        Map<String, Object> response = new HashMap<>();
        response.put("exito", true);
        response.put("mensaje", "Reserva realizada");

        when(restTemplate.postForEntity(
                eq(INVENTARIO_BASE_URL + "/reservar"),
                any(),
                eq(Map.class)
        )).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // When
        boolean resultado = inventarioRestAdapter.reservarInventario(request);

        // Then
        assertTrue(resultado);
        verify(restTemplate, times(1)).postForEntity(
                eq(INVENTARIO_BASE_URL + "/reservar"),
                any(),
                eq(Map.class)
        );
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe retornar false cuando la reserva de inventario falla")
    void debeRetornarFalse_CuandoReservaFalla() {
        // Given
        Map<String, Object> request = crearRequestReserva();
        Map<String, Object> response = new HashMap<>();
        response.put("exito", false);
        response.put("mensaje", "Stock insuficiente");

        when(restTemplate.postForEntity(
                eq(INVENTARIO_BASE_URL + "/reservar"),
                any(),
                eq(Map.class)
        )).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // When
        boolean resultado = inventarioRestAdapter.reservarInventario(request);

        // Then
        assertFalse(resultado);
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe lanzar excepción cuando el servicio de inventario no responde")
    void debeLanzarExcepcion_CuandoServicioNoResponde() {
        // Given
        Map<String, Object> request = crearRequestReserva();

        when(restTemplate.postForEntity(
                anyString(),
                any(),
                any(Class.class)
        )).thenThrow(new RestClientException("Connection refused"));

        // When & Then
        assertThrows(Exception.class, () -> {
            inventarioRestAdapter.reservarInventario(request);
        });
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe usar timeout configurado para las peticiones")
    void debeUsarTimeoutConfigurado() {
        // Given
        Map<String, Object> request = crearRequestReserva();
        Map<String, Object> response = new HashMap<>();
        response.put("exito", true);

        when(restTemplate.postForEntity(
                anyString(),
                any(),
                any(Class.class)
        )).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // When
        inventarioRestAdapter.reservarInventario(request);

        // Then
        verify(config, times(1)).getTimeout();
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe construir URL correcta para reservar inventario")
    void debeConstruirURLCorrecta() {
        // Given
        Map<String, Object> request = crearRequestReserva();
        Map<String, Object> response = new HashMap<>();
        response.put("exito", true);

        when(restTemplate.postForEntity(
                anyString(),
                any(),
                any(Class.class)
        )).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // When
        inventarioRestAdapter.reservarInventario(request);

        // Then
        verify(restTemplate).postForEntity(
                eq(INVENTARIO_BASE_URL + "/reservar"),
                any(),
                any(Class.class)
        );
    }

    private Map<String, Object> crearRequestReserva() {
        Map<String, Object> request = new HashMap<>();
        request.put("productoId", UUID.randomUUID().toString());
        request.put("cantidad", 5);
        request.put("ordenId", UUID.randomUUID().toString());
        return request;
    }
}