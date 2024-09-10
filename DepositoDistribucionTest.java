import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositoDistribucionTest {

    private DepositoDistribucion deposito;
    private Producto productoA;
    private Producto productoB;
    private Producto productoFinA;
    private Producto productoFinB;

    @BeforeEach
    public void setUp() {
        // Inicializa el depósito con capacidad 10
        deposito = new DepositoDistribucion(10);

        // Inicializa productos de prueba
        productoA = new Producto("A", 1);
        productoB = new Producto("B", 2);
        productoFinA = new Producto("FIN_A", 3);
        productoFinB = new Producto("FIN_B", 4);
    }

    @Test
    public void testGuardarProducto() {
        deposito.guardarProducto(productoA);
        assertEquals(9, deposito.getCapDepDist(), "La capacidad del depósito debería disminuir en 1");
        assertTrue(deposito.hayProductoTipo("A"), "Debería haber productos de tipo A en el depósito");
    }

    @Test
    public void testSacarProductoTipoA() {
        deposito.guardarProducto(productoA);
        Producto p = deposito.sacarProducto("A", 1);
        assertEquals(productoA, p, "El producto sacado debería ser el que se guardó");
        assertEquals(10, deposito.getCapDepDist(), "La capacidad del depósito debería aumentar en 1");
    }

    @Test
    public void testSacarProductoTipoB() {
        deposito.guardarProducto(productoB);
        Producto p = deposito.sacarProducto("B", 1);
        assertEquals(productoB, p, "El producto sacado debería ser el que se guardó");
        assertEquals(10, deposito.getCapDepDist(), "La capacidad del depósito debería aumentar en 1");
    }

    @Test
    public void testTraerProductoGeneral() {
        deposito.guardarProducto(productoFinA);
        Producto p = deposito.traerProductoGeneral("A", 1);
        assertEquals(productoFinA, p, "El producto general debería ser el tipo FIN_A");
        assertEquals(10, deposito.getCapDepDist(), "La capacidad del depósito debería aumentar en 1");
    }

    @Test
    public void testTraerProductoNoTerminal() {
        deposito.guardarProducto(productoA);
        Producto p = deposito.traerProductoNoTerminal("A");
        assertEquals(productoA, p, "El producto no terminal debería ser el tipo A");
        assertEquals(10, deposito.getCapDepDist(), "La capacidad del depósito debería aumentar en 1");
    }

    @Test
    public void testHayProductoTipo() {
        deposito.guardarProducto(productoA);
        assertTrue(deposito.hayProductoTipo("A"), "Debería haber productos de tipo A");
        assertFalse(deposito.hayProductoTipo("B"), "No debería haber productos de tipo B");
    }
}
