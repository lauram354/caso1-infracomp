import java.util.List;
import java.util.ArrayList;

public class DepositoDistribucion {
    private int capDepDist;
    private List<Producto> productos = new ArrayList<>();

    public DepositoDistribucion(int capDepDist) {
        this.capDepDist = capDepDist;
    }
    
    public synchronized void guardarProducto(Producto p){
        while (capDepDist == 0){
            try {
                System.out.println("el operario interno esta esperando para guardar productos");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Se guardo un producto tipo " + p.getTipo() + " en el deposito de distribucion");
        this.productos.add(p);
        this.capDepDist --;    
    }
}
