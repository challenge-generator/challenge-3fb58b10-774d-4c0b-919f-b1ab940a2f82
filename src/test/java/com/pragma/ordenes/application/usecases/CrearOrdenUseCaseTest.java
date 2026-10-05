package com.pragma.ordenes.application.usecases;


import com.pragma.ordenes.domain.model.EstadoOrden;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.model.Transaccion;
import com.pragma.ordenes.domain.ports.InventarioServicePort;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import com.pragma.ordenes.domain.ports.TransaccionRepositoryPort;
import com.pragma.ordenes.infrastructure.exceptions.InventarioServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitarios para CrearOrdenUseCase")
class CrearOrdenUseCaseTest {

    @Mock
    private OrdenRepositoryPort ordenRepository;

    @Mock
    private InventarioServicePort inventarioService;

    @Mock
    private TransaccionRepositoryPort transaccionRepository;

    @InjectMocks
    private CrearOrdenUseCase crearOrdenUseCase;

    private Orden ordenValida;

    @BeforeEach
    void setUp() {
        ordenValida = new Orden();
        ordenValida.setId(UUID.randomUUID());
        ordenValida.setClienteId("cliente-123");
        ordenValida.setItems(new ArrayList<>());
        ordenValida.setEstado(Orden.EstadoOrden.PENDIENTE);
        ordenValida.setFechaCreacion(LocalDateTime.now());
        ordenValida.setFechaActualizacion(LocalDateTime.now());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe crear una orden exitosamente cuando el inventario responde correctamente")
    void debeCrearOrdenExitosamente_CuandoInventarioResponde() {
        // Given
        when(inventarioService.reservarInventario(any())).thenReturn(true);
        when(ordenRepository.guardar(any(Orden.class))).thenReturn(ordenValida);
        when(transaccionRepository.guardar(any(Transaccion.class))).thenAnswer(i -> i.getArgument(0));

        // When
        Orden resultado = crearOrdenUseCase.ejecutar(ordenValida);

        // Then
        assertNotNull(resultado);
        verify(ordenRepository, times(1)).guardar(any(Orden.class));
        verify(inventarioService, times(1)).reservarInventario(any());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe lanzar excepción cuando el inventario no tiene disponibilidad")
    void debeLanzarExcepcion_CuandoInventarioNoDisponible() {
        // Given
        when(inventarioService.reservarInventario(any())).thenReturn(false);

        // When & Then
        assertThrows(InventarioServiceException.class, () -> {
            crearOrdenUseCase.ejecutar(ordenValida);
        });
        verify(ordenRepository, never()).guardar(any(Orden.class));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe registrar transacción al crear orden exitosamente")
    void debeRegistrarTransaccion_CuandoOrdenCreada() {
        // Given
        when(inventarioService.reservarInventario(any())).thenReturn(true);
        when(ordenRepository.guardar(any(Orden.class))).thenReturn(ordenValida);
        when(transaccionRepository.guardar(any(Transaccion.class))).thenAnswer(i -> i.getArgument(0));

        // When
        crearOrdenUseCase.ejecutar(ordenValida);

        // Then
        verify(transaccionRepository, times(1)).guardar(any(Transaccion.class));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe validar que la orden tenga items antes de procesar")
    void debeValidarItems_CuandoOrdenNoTieneItems() {
        // Given
        ordenValida.setItems(new ArrayList<>());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            crearOrdenUseCase.ejecutar(ordenValida);
        });
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.Disabled("Superficie de práctica: implementar según criterios del reto")
    @DisplayName("Debe manejar error de conexión con inventario")
    void debeManejarErrorConexionInventario() {
        // Given
        when(inventarioService.reservarInventario(any()))
                .thenThrow(new InventarioServiceException("Error de conexión con inventario"));

        // When & Then
        assertThrows(InventarioServiceException.class, () -> {
            crearOrdenUseCase.ejecutar(ordenValida);
        });
    }
}