import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

public class DepositoProduccionTest {

    private DepositoProduccion deposito;
    private Producto productoA;
    private Producto productoB;
    private Producto productoTerminal;

    @BeforeEach
    public void setUp() {
        
        deposito = new DepositoProduccion(10);

        
        productoA = new Producto("A", 1);
        productoB = new Producto("B", 2);
        productoTerminal = new Producto("T", 3);
    }

    @Test
    public void testGuardarProducto() {
        deposito.guardarProducto(productoA, 5);
        assertEquals(9, deposito.getCapDepProd(), "La capacidad del depósito debería disminuir en 1");
        assertTrue(deposito.hayProductos(), "Debería haber productos en el depósito");
    }

    @Test
    public void testSacarProducto() {
        deposito.guardarProducto(productoA, 5);
        Producto p = deposito.sacarProducto();
        assertEquals(productoA, p, "El producto sacado debería ser el que se guardó");
        assertEquals(10, deposito.getCapDepProd(), "La capacidad del depósito debería aumentar en 1");
        assertFalse(deposito.hayProductos(), "No debería haber productos en el depósito");
    }


    @Test
    public void testHayProductos() {
        assertFalse(deposito.hayProductos(), "Al inicio no debería haber productos");
        deposito.guardarProducto(productoA, 5);
        assertTrue(deposito.hayProductos(), "Debería haber productos después de guardar uno");
    }
}

