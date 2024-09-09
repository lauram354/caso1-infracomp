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

    public synchronized void guardarProducto(Producto p) {
        while (capDepProd == 0) {
            try {
                System.out.println("un productor esta esperando");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Se guardo un producto tipo " + p.getTipo());

        if (p.getTipo().equals("A") | p.getTipo().equals("B")) {
            this.productosNoTerminales++;
        }

        this.productos.add(p);
        this.capDepProd--;

    }

    public synchronized Producto sacarProducto() {
        /* TODO: revisar problema de lista vacía */
        Producto p = productos.get(0);
        productos.remove(0);
        if (p.getTipo().equals("A") | p.getTipo().equals("B")) {
            this.productosNoTerminales--;
        }
        this.capDepProd++;
        System.out.println("Se libero un espacio en el deposito de produccion");
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
                System.out.println("un producto terminal esta esperando para ser guardado");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        guardarProducto(p);

    }
}
