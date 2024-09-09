import java.util.List;
import java.util.ArrayList;

public class DepositoDistribucion {
    private int capDepDist;
    private List<Producto> productos = new ArrayList<>();

    public DepositoDistribucion(int capDepDist) {
        this.capDepDist = capDepDist;
    }

    public synchronized void guardarProducto(Producto p) {
        while (capDepDist == 0) {
            try {
                System.out.println("el operario interno esta esperando para guardar productos");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Se guardo un producto tipo " + p.getTipo() + " en el deposito de distribucion");
        this.productos.add(p);
        this.capDepDist--;
        notifyAll();
    }

    public synchronized Producto sacarProducto(String tipo) {
        while (true) {
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getTipo().equals(tipo)) {
                    Producto p = productos.remove(i);
                    System.out.println("Se saco un producto tipo " + p.getTipo() + " del deposito de distribucion");
                    this.capDepDist++;
                    notifyAll();
                    return p;
                }
            }
            try {
                System.out.println("Distribuidor esperando por producto tipo " + tipo);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Boolean hayProductos() {
        return this.productos.size() > 0;
    }

    public synchronized void agregarProductoTerminal(Producto p) {
        while (productos.size() > 0) {
            try {
                System.out.println("el operario externo esta esperando para agregar productos terminales");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Se agrego un producto terminal tipo " + p.getTipo() + " al deposito de distribucion");
        this.productos.add(p);
        this.capDepDist--;
        notifyAll();
    }
}