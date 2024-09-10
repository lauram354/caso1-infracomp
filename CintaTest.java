import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CintaTest {

    private Cinta cinta;
    private Producto producto;

    @BeforeEach
    public void setUp() {
        
        cinta = new Cinta(false);

        
        producto = new Producto("A", 1);
    }

    @Test
    public void testGetOcupado() {
        
        assertFalse(cinta.getOcupado(), "Al principio, la cinta no debería estar ocupada");

        
        cinta.setOcupado(true);
        assertTrue(cinta.getOcupado(), "La cinta debería estar ocupada después de ser establecida como tal");
    }

    @Test
    public void testGuardarProducto() {
        
        cinta.guardarProducto(producto);

        
        assertTrue(cinta.getOcupado(), "La cinta debería estar ocupada después de guardar un producto");

        
        Producto sacado = cinta.sacaProducto();
        assertEquals(producto, sacado, "El producto sacado debería ser el mismo que el producto guardado");
    }

    @Test
    public void testSacaProducto() {
        
        cinta.guardarProducto(producto);
        Producto p = cinta.sacaProducto();

        
        assertEquals(producto, p, "El producto sacado debería ser el mismo que el producto guardado");

        
        assertFalse(cinta.getOcupado(), "La cinta debería estar desocupada después de sacar el producto");
    }

    @Test
    public void testSacaProductoCuandoVacia() {
        
        assertThrows(IndexOutOfBoundsException.class, () -> {
            cinta.sacaProducto();
        }, "Debería lanzar IndexOutOfBoundsException al intentar sacar un producto de una cinta vacía");
    }
}

