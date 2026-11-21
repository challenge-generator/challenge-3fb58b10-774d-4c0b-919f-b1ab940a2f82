package com.pragma.ordenes.application.usecases;

import com.pragma.ordenes.application.exceptions.InventarioInsuficienteException;
import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.ports.InventarioServicePort;
import com.pragma.ordenes.domain.ports.OrdenRepositoryPort;
import com.pragma.ordenes.infrastructure.exceptions.InventarioServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitarios para CrearOrdenUseCase")
class CrearOrdenUseCaseTest {

    @Mock
    private OrdenRepositoryPort ordenRepository;

    @Mock
    private InventarioServicePort inventarioService;

    @Mock
    private RegistarTransaccionUseCase registarTransaccionUseCase;

    @InjectMocks
    private CrearOrdenUseCase crearOrdenUseCase;

    private Orden ordenValida;

    @BeforeEach
    void setUp() {
        ordenValida = Orden.builder()
                .clienteId("cliente-123")
                .items(List.of(Orden.ItemOrden.builder()
                        .productoId(UUID.randomUUID())
                        .nombreProducto("Producto")
                        .cantidad(2)
                        .precioUnitario(new BigDecimal("10.00"))
                        .build()))
                .build();
    }

    @Test
    @DisplayName("Crea y persiste la orden cuando el inventario está disponible")
    void debeCrearOrdenCuandoInventarioEstaDisponible() throws Exception {
        when(inventarioService.verificarDisponibilidad(ordenValida.getItems())).thenReturn(true);
        when(ordenRepository.guardar(any(Orden.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Orden resultado = crearOrdenUseCase.ejecutar(ordenValida);

        assertSame(ordenValida, resultado);
        assertNotNull(resultado.getId());
        assertNotNull(resultado.getFechaCreacion());
        assertNotNull(resultado.getFechaActualizacion());
        assertEquals(Orden.EstadoOrden.PENDIENTE, resultado.getEstado());
        verify(ordenRepository).guardar(ordenValida);
        verify(inventarioService).decrementarStock(ordenValida.getItems());
        verify(registarTransaccionUseCase).registrarTransaccionOrden(ordenValida);
        verify(registarTransaccionUseCase).registrarTransactionInventario(ordenValida);
    }

    @Test
    @DisplayName("Lanza InventarioInsuficienteException sin persistir si no hay stock")
    void debeLanzarExcepcionCuandoInventarioNoEstaDisponible() throws Exception {
        when(inventarioService.verificarDisponibilidad(ordenValida.getItems())).thenReturn(false);

        assertThrows(InventarioInsuficienteException.class,
                () -> crearOrdenUseCase.ejecutar(ordenValida));

        verify(ordenRepository, never()).guardar(any(Orden.class));
        verify(inventarioService, never()).decrementarStock(anyList());
        verifyNoInteractions(registarTransaccionUseCase);
    }

    @Test
    @DisplayName("Rechaza una orden sin items antes de consultar inventario")
    void debeRechazarOrdenSinItems() throws Exception {
        ordenValida.setItems(List.of());

        assertThrows(IllegalArgumentException.class,
                () -> crearOrdenUseCase.ejecutar(ordenValida));

        verifyNoInteractions(inventarioService, ordenRepository, registarTransaccionUseCase);
    }

    @Test
    @DisplayName("Restaura el inventario y lanza excepción si falla el decremento")
    void debeRestaurarInventarioSiFallaElDecremento() throws Exception {
        when(inventarioService.verificarDisponibilidad(ordenValida.getItems())).thenReturn(true);
        when(ordenRepository.guardar(any(Orden.class))).thenAnswer(invocation -> invocation.getArgument(0));
        doThrow(new IllegalStateException("Servicio no disponible"))
                .when(inventarioService).decrementarStock(ordenValida.getItems());

        InventarioServiceException exception = assertThrows(InventarioServiceException.class,
                () -> crearOrdenUseCase.ejecutar(ordenValida));

        assertTrue(exception.getMessage().contains("Servicio no disponible"));
        verify(inventarioService).restaurarStock(ordenValida.getItems());
        verifyNoInteractions(registarTransaccionUseCase);
    }

    @Test
    @DisplayName("Rechaza una orden cuyo cliente no está informado")
    void debeRechazarOrdenSinCliente() throws Exception {
        ordenValida.setClienteId(" ");

        assertThrows(IllegalArgumentException.class,
                () -> crearOrdenUseCase.ejecutar(ordenValida));

        verifyNoInteractions(inventarioService, ordenRepository, registarTransaccionUseCase);
    }
}
