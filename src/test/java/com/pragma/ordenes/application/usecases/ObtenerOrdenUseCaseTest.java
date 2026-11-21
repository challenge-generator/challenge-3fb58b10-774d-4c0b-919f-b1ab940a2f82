package com.pragma.ordenes.application.usecases;

import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitarios para ObtenerOrdenUseCase")
class ObtenerOrdenUseCaseTest {

    @Mock
    private OrdenRepositoryPort ordenRepositoryPort;

    @InjectMocks
    private ObtenerOrdenUseCase obtenerOrdenUseCase;

    private UUID ordenId;
    private Orden orden;

    @BeforeEach
    void setUp() {
        ordenId = UUID.randomUUID();
        orden = Orden.builder()
                .id(ordenId)
                .clienteId("cliente-123")
                .estado(Orden.EstadoOrden.PENDIENTE)
                .build();
    }

    @Test
    @DisplayName("Retorna la orden cuando existe el ID")
    void debeRetornarOrdenExistente() {
        when(ordenRepositoryPort.buscarPorId(ordenId)).thenReturn(Optional.of(orden));

        Orden resultado = obtenerOrdenUseCase.buscarPorId(ordenId);

        assertSame(orden, resultado);
        verify(ordenRepositoryPort).buscarPorId(ordenId);
    }

    @Test
    @DisplayName("Rechaza la búsqueda cuando el ID es nulo")
    void debeRechazarIdNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> obtenerOrdenUseCase.buscarPorId(null));

        verifyNoInteractions(ordenRepositoryPort);
    }

    @Test
    @DisplayName("Lanza una excepción cuando no existe una orden con el ID")
    void debeIndicarCuandoLaOrdenNoExiste() {
        when(ordenRepositoryPort.buscarPorId(ordenId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> obtenerOrdenUseCase.buscarPorId(ordenId));

        verify(ordenRepositoryPort).buscarPorId(ordenId);
    }

    @Test
    @DisplayName("Retorna las órdenes asociadas a un cliente")
    void debeRetornarOrdenesPorCliente() {
        when(ordenRepositoryPort.buscarPorClienteId("cliente-123"))
                .thenReturn(List.of(orden));

        List<Orden> resultado = obtenerOrdenUseCase.buscarPorClienteId("cliente-123");

        assertEquals(List.of(orden), resultado);
        verify(ordenRepositoryPort).buscarPorClienteId("cliente-123");
    }

    @Test
    @DisplayName("Rechaza la búsqueda por cliente cuando el identificador es nulo")
    void debeRechazarClienteNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> obtenerOrdenUseCase.buscarPorClienteId(null));

        verifyNoInteractions(ordenRepositoryPort);
    }

    @Test
    @DisplayName("Retorna una lista vacía cuando el cliente no tiene órdenes")
    void debeRetornarListaVaciaCuandoElClienteNoTieneOrdenes() {
        when(ordenRepositoryPort.buscarPorClienteId("cliente-sin-ordenes"))
                .thenReturn(List.of());

        List<Orden> resultado = obtenerOrdenUseCase.buscarPorClienteId("cliente-sin-ordenes");

        assertTrue(resultado.isEmpty());
        verify(ordenRepositoryPort).buscarPorClienteId("cliente-sin-ordenes");
    }

    @Test
    @DisplayName("Retorna las órdenes asociadas a un estado")
    void debeRetornarOrdenesPorEstado() {
        when(ordenRepositoryPort.buscarPorEstado(Orden.EstadoOrden.PENDIENTE))
                .thenReturn(List.of(orden));

        List<Orden> resultado = obtenerOrdenUseCase.buscarPorEstado(Orden.EstadoOrden.PENDIENTE);

        assertEquals(List.of(orden), resultado);
        verify(ordenRepositoryPort).buscarPorEstado(Orden.EstadoOrden.PENDIENTE);
    }

    @Test
    @DisplayName("Rechaza la búsqueda por estado cuando el estado es nulo")
    void debeRechazarEstadoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> obtenerOrdenUseCase.buscarPorEstado(null));

        verifyNoInteractions(ordenRepositoryPort);
    }

    @Test
    @DisplayName("Propaga el error del repositorio al buscar por estado")
    void debePropagarErrorDelRepositorioPorEstado() {
        when(ordenRepositoryPort.buscarPorEstado(Orden.EstadoOrden.CANCELADA))
                .thenThrow(new IllegalStateException("Repositorio no disponible"));

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> obtenerOrdenUseCase.buscarPorEstado(Orden.EstadoOrden.CANCELADA));

        assertEquals("Repositorio no disponible", exception.getMessage());
        verify(ordenRepositoryPort).buscarPorEstado(Orden.EstadoOrden.CANCELADA);
    }
}

