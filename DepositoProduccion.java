import java.util.List;
import java.util.ArrayList;

public class DepositoProduccion {
    private int capDepProd;
    private List<Producto> productos = new ArrayList<>();
    private int productosNoTerminales = 0;

    public DepositoProduccion(int capDepProd) {
        this.capDepProd = capDepProd;
    }

    public int getCapDepProd() {
        return capDepProd;
    }

    public synchronized void guardarProducto(Producto p, int restantes) {
        while (capDepProd == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (p.getTipo().equals("A") | p.getTipo().equals("B")) {
            this.productosNoTerminales++;
        }

        this.productos.add(p);
        System.out.println(
                "Productor " + String.valueOf(p.getIdProductorEncargado()) + ": produjo un producto " + p.getTipo()
                        + ". Restantes: " + restantes);
        this.capDepProd--;

    }

    public synchronized Producto sacarProducto() {
        /* TODO: revisar problema de lista vacía */
        while (this.productos.size() == 0) {
            Thread.yield();
        }
        Producto p = productos.get(0);
        productos.remove(0);
        System.out.println("Un producto de tipo " + p.getTipo() + " fue sacado del deposito de produccion");
        if (p.getTipo().equals("A") | p.getTipo().equals("B")) {
            this.productosNoTerminales--;
        }
        this.capDepProd++;
        notify();
        return p;
    }

    public synchronized Boolean hayProductos() {
        if (this.productos.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public synchronized void agregarProductoTerminal(Producto p) {
        while (productosNoTerminales > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        guardarProducto(p, 0);

    }
}
