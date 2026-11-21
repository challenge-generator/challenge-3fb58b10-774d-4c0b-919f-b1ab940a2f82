package com.pragma.ordenes.application.usecases;

import com.pragma.ordenes.application.exceptions.InventarioNoCancelableException;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.ports.InventarioServicePort;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitarios para CancelarOrdenUseCase")
class CancelarOrdenUseCaseTest {

    @Mock
    private OrdenRepositoryPort ordenRepository;

    @Mock
    private InventarioServicePort inventarioService;

    @Mock
    private RegistarTransaccionUseCase registarTransaccionUseCase;

    @InjectMocks
    private CancelarOrdenUseCase cancelarOrdenUseCase;

    private Orden orden;

    @BeforeEach
    void setUp() {
        orden = Orden.builder()
                .id(UUID.randomUUID())
                .clienteId("cliente-123")
                .items(List.of(Orden.ItemOrden.builder()
                        .productoId(UUID.randomUUID())
                        .nombreProducto("Producto")
                        .cantidad(2)
                        .precioUnitario(new BigDecimal("10.00"))
                        .build()))
                .estado(Orden.EstadoOrden.PENDIENTE)
                .fechaCreacion(LocalDateTime.now().minusHours(1))
                .fechaActualizacion(LocalDateTime.now().minusMinutes(30))
                .build();
    }

    @Test
    @DisplayName("Cancela una orden existente y registra las transacciones")
    void debeCancelarOrdenExistente() throws Exception {
        when(ordenRepository.buscarPorId(orden.getId())).thenReturn(Optional.of(orden));
        when(ordenRepository.guardar(any(Orden.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Orden resultado = cancelarOrdenUseCase.cancelarOrden(orden.getId());

        assertSame(orden, resultado);
        assertEquals(Orden.EstadoOrden.CANCELADA, resultado.getEstado());
        assertNotNull(resultado.getFechaActualizacion());
        verify(ordenRepository).buscarPorId(orden.getId());
        verify(ordenRepository).guardar(orden);
        verify(inventarioService).decrementarStock(orden.getItems());
        verify(registarTransaccionUseCase).registrarTransaccionOrden(orden);
        verify(registarTransaccionUseCase).registrarTransactionInventario(orden);
    }

    @Test
    @DisplayName("Rechaza la cancelación cuando el ID de la orden es nulo")
    void debeRechazarOrdenConIdNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> cancelarOrdenUseCase.cancelarOrden(null));

        verifyNoInteractions(ordenRepository, inventarioService, registarTransaccionUseCase);
    }

    @Test
    @DisplayName("Restaura el inventario y no registra transacciones si falla la cancelación")
    void debeRestaurarInventarioSiFallaLaCancelacion() throws Exception {
        when(ordenRepository.buscarPorId(orden.getId())).thenReturn(Optional.of(orden));
        when(ordenRepository.guardar(any(Orden.class))).thenAnswer(invocation -> invocation.getArgument(0));
        doThrow(new IllegalStateException("Servicio no disponible"))
                .when(inventarioService).decrementarStock(orden.getItems());

        InventarioNoCancelableException exception = assertThrows(InventarioNoCancelableException.class,
                () -> cancelarOrdenUseCase.cancelarOrden(orden.getId()));

        assertTrue(exception.getMessage().contains("Servicio no disponible"));
        verify(inventarioService).restaurarStock(orden.getItems());
        verifyNoInteractions(registarTransaccionUseCase);
    }

    @Test
    @DisplayName("Propaga el error si no se puede persistir la orden cancelada")
    void debePropagarErrorSiFallaLaPersistencia() {
        when(ordenRepository.buscarPorId(orden.getId())).thenReturn(Optional.of(orden));
        when(ordenRepository.guardar(orden)).thenThrow(new IllegalStateException("Error de persistencia"));

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> cancelarOrdenUseCase.cancelarOrden(orden.getId()));

        assertEquals("Error de persistencia", exception.getMessage());
        verifyNoInteractions(inventarioService, registarTransaccionUseCase);
    }
}