package com.pragma.ordenes.application.usecases;

import com.pragma.ordenes.domain.model.Orden;
import com.pragma.ordenes.domain.model.Transaccion;
import com.pragma.ordenes.domain.ports.TransaccionRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitarios para RegistarTransaccionUseCase")
class RegistarTransaccionUseCaseTest {

    @Mock
    private TransaccionRepositoryPort transaccionRepository;

    @InjectMocks
    private RegistarTransaccionUseCase useCase;

    private Orden orden;
    private UUID ordenId;
    private UUID productoId;

    @BeforeEach
    void setUp() {
        ordenId = UUID.randomUUID();
        productoId = UUID.randomUUID();
        orden = Orden.builder()
                .id(ordenId)
                .clienteId("cliente-123")
                .items(List.of(Orden.ItemOrden.builder()
                                .productoId(productoId)
                                .nombreProducto("Producto")
                                .cantidad(3)
                                .precioUnitario(new BigDecimal("12.50"))
                                .build(),
                        Orden.ItemOrden.builder()
                                .productoId(UUID.randomUUID())
                                .nombreProducto("Otro producto")
                                .cantidad(1)
                                .precioUnitario(new BigDecimal("4.00"))
                                .build()))
                .build();
    }

    @Test
    @DisplayName("Registra una transacción de creación para la orden")
    void debeRegistrarTransaccionDeOrden() {
        ArgumentCaptor<Transaccion> captor = ArgumentCaptor.forClass(Transaccion.class);

        useCase.registrarTransaccionOrden(orden);

        verify(transaccionRepository).guardar(captor.capture());
        Transaccion transaccion = captor.getValue();
        assertNotNull(transaccion.getId());
        assertEquals("CREACION", transaccion.getTipoOperacion());
        assertEquals("ORDEN", transaccion.getEntidadAfectada());
        assertEquals(ordenId, transaccion.getEntidadId());
        assertTrue(transaccion.getDetalles().contains("cliente-123"));
        assertTrue(transaccion.getDetalles().contains("2 items"));
        assertNotNull(transaccion.getFechaTransaccion());
    }

    @Test
    @DisplayName("Registra una transacción de inventario por cada item")
    void debeRegistrarTransaccionPorCadaItemDeInventario() {
        ArgumentCaptor<Transaccion> captor = ArgumentCaptor.forClass(Transaccion.class);

        useCase.registrarTransactionInventario(orden);

        verify(transaccionRepository, times(2)).guardar(captor.capture());
        List<Transaccion> transacciones = captor.getAllValues();
        assertEquals(2, transacciones.size());
        assertEquals("DECREMENTO_STOCK", transacciones.get(0).getTipoOperacion());
        assertEquals("INVENTARIO", transacciones.get(0).getEntidadAfectada());
        assertEquals(productoId, transacciones.get(0).getEntidadId());
        assertTrue(transacciones.get(0).getDetalles().contains("3 unidades"));
        assertEquals("INVENTARIO", transacciones.get(1).getEntidadAfectada());
    }
}
