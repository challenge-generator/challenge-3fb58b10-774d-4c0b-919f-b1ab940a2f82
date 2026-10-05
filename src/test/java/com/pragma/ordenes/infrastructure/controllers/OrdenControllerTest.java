package com.pragma.ordenes.infrastructure.controllers;


import com.pragma.ordenes.domain.model.EstadoOrden;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.ordenes.application.usecases.CrearOrdenUseCase;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.infrastructure.exceptions.GlobalExceptionHandler;
import com.pragma.ordenes.infrastructure.exceptions.InventarioServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrdenController.class)
@Import(GlobalExceptionHandler.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Tests de integración para OrdenController")
class OrdenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CrearOrdenUseCase crearOrdenUseCase;

    private Orden ordenRequest;
    private Orden ordenResponse;

    @BeforeEach
    void setUp() {
        ordenRequest = new Orden();
        ordenRequest.setClienteId("cliente-123");
        ordenRequest.setItems(new ArrayList<>());

        ordenResponse = new Orden();
        ordenResponse.setId(UUID.randomUUID());
        ordenResponse.setClienteId("cliente-123");
        ordenResponse.setItems(new ArrayList<>());
        ordenResponse.setEstado(Orden.EstadoOrden.PENDIENTE);
        ordenResponse.setFechaCreacion(LocalDateTime.now());
        ordenResponse.setFechaActualizacion(LocalDateTime.now());
    }

    @Test
    @DisplayName("Debe crear una orden exitosamente cuando los datos son válidos")
    void debeCrearOrdenExitosamente() throws Exception {
        // Given
        when(crearOrdenUseCase.ejecutar(any(Orden.class))).thenReturn(ordenResponse);

        // When & Then
        mockMvc.perform(post("/api/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordenRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.clienteId").value("cliente-123"))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));

        verify(crearOrdenUseCase, times(1)).ejecutar(any(Orden.class));
    }

    @Test
    @DisplayName("Debe retornar 400 cuando el clienteId está vacío")
    void debeRetornar400_CuandoClienteIdVacio() throws Exception {
        // Given
        ordenRequest.setClienteId("");

        // When & Then
        mockMvc.perform(post("/api/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordenRequest)))
                .andExpect(status().isBadRequest());

        verify(crearOrdenUseCase, never()).ejecutar(any(Orden.class));
    }

    @Test
    @DisplayName("Debe manejar excepción de inventario y retornar 503")
    void debeManejarExcepcionInventario() throws Exception {
        // Given
        when(crearOrdenUseCase.ejecutar(any(Orden.class)))
                .thenThrow(new InventarioServiceException("Inventario no disponible"));

        // When & Then
        mockMvc.perform(post("/api/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordenRequest)))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.mensaje").exists());
    }

    @Test
    @DisplayName("Debe retornar 400 cuando la orden no tiene items")
    void debeRetornar400_SinItems() throws Exception {
        // Given
        ordenRequest.setItems(new ArrayList<>());

        // When & Then
        mockMvc.perform(post("/api/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordenRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Debe obtener una orden por ID exitosamente")
    void debeObtenerOrdenPorId() throws Exception {
        // Given
        UUID ordenId = UUID.randomUUID();
        ordenResponse.setId(ordenId);

        // When & Then
        mockMvc.perform(get("/api/ordenes/" + ordenId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ordenId.toString()));
    }

    @Test
    @DisplayName("Debe retornar 404 cuando la orden no existe")
    void debeRetornar404_CuandoOrdenNoExiste() throws Exception {
        // Given
        UUID ordenId = UUID.randomUUID();

        // When & Then
        mockMvc.perform(get("/api/ordenes/" + ordenId))
                .andExpect(status().isNotFound());
    }
}