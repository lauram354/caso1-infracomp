import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class DepositoDistribucion {
    private int capDepDist;
    private List<Producto> productos = new ArrayList<>();
    private final Semaphore semA = new Semaphore(0);
    private final Semaphore semB = new Semaphore(0);
    private final Semaphore mutex = new Semaphore(1);

    public DepositoDistribucion(int capDepDist) {
        this.capDepDist = capDepDist;
    }

    public void guardarProducto(Producto p) {
        P(mutex);
        while (capDepDist == 0) {
            try {
                System.out.println("el operario interno esta esperando para guardar productos");
                V(mutex);
                Thread.sleep(100); // Sleep to avoid busy waiting
                P(mutex);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Se guardo un producto tipo " + p.getTipo() + " en el deposito de distribucion");
        this.productos.add(p);
        this.capDepDist--;
        V(mutex);
        notifySemaphore(p.getTipo());
    }

    private void notifySemaphore(String tipo) {
        if (tipo.equals("A")) {
            V(semA);
        } else if (tipo.equals("B")) {
            V(semB);
        }
    }

    public Producto sacarProducto(String tipo) {
        P(getSemaphore(tipo));
        P(mutex);
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getTipo().equals(tipo)) {
                Producto p = productos.remove(i);
                System.out.println("Se saco un producto tipo " + p.getTipo() + " del deposito de distribucion");
                this.capDepDist++;
                V(mutex);
                return p;
            }
        }
        V(mutex);
        return null; // This should never happen
    }

    private Semaphore getSemaphore(String tipo) {
        if (tipo.equals("A")) {
            return semA;
        } else if (tipo.equals("B")) {
            return semB;
        } else {
            throw new IllegalArgumentException("Tipo de producto desconocido: " + tipo);
        }
    }

    public synchronized Boolean hayProductos() {
        return this.productos.size() > 0;
    }

    public void agregarProductoTerminal(Producto p) {
        P(mutex);
        while (productos.size() > 0) {
            try {
                System.out.println("el operario externo esta esperando para agregar productos terminales");
                V(mutex);
                Thread.sleep(100); // Sleep to avoid busy waiting
                P(mutex);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Se agrego un producto terminal tipo " + p.getTipo() + " al deposito de distribucion");
        this.productos.add(p);
        this.capDepDist--;
        V(mutex);
    }

    private void P(Semaphore sem) {
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void V(Semaphore sem) {
        sem.release();
    }
}