import java.util.List;
import java.util.ArrayList;

public class DepositoDistribucion {
    private int capDepDist;
    private List<Producto> productos = new ArrayList<>();
    private int productosNoTerminalesA;
    private int productosNoTerminalesB;

    public DepositoDistribucion(int capDepDist) {
        this.capDepDist = capDepDist;
    }
    
    public synchronized void guardarProducto(Producto p){
        System.out.println("Se guardo un producto tipo " + p.getTipo() + " en el deposito de distribucion");
        if (p.getTipo().equals("A")){
            productosNoTerminalesA ++;
        }
        if (p.getTipo().equals("B")){
            productosNoTerminalesB ++;
        }
        this.productos.add(p);
        this.capDepDist --;
        notifyAll();    
    }

    public synchronized Producto sacarProducto(String tipo){
        while(this.productos.size() == 0 || !hayProductoTipo(tipo)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Producto p = productos.get(0);
        if (tipo.equals("A")){
            if (productosNoTerminalesA == 0){
                p = traerProductoGeneral("A");
            }else{
                p = traerProductoNoTerminal("A");
                productosNoTerminalesA --;
            }
        }else if(tipo.equals("B")){
            if (productosNoTerminalesB == 0){
                p = traerProductoGeneral("B");
            }else{
                p= traerProductoNoTerminal("B");
                productosNoTerminalesB --;
            }

        }

    
        return p;
    }

    public synchronized Producto traerProductoGeneral(String tipo){
        Producto p = productos.get(0);

        for (int i = 0; i < productos.size(); i++) {
            p = productos.get(i);
            if (p.getTipo().equals("FIN_" + tipo)){
                this.productos.remove(i);
                capDepDist ++;
                break;
            }
        }
        return p;
    }

    public synchronized Producto traerProductoNoTerminal(String tipo){
        Producto p = productos.get(0);

        for (int i = 0; i < productos.size(); i++) {
            p = productos.get(i);
            if (p.getTipo().equals(tipo)){
                this.productos.remove(i);
                capDepDist ++;
                break;
            }
        }
        return p;
    }

    public synchronized Boolean hayProductoTipo(String tipo){
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getTipo().equals(tipo) || p.getTipo().equals("FIN_" + tipo)){
                return true;
            }
        }
        return false;
    }

    public synchronized int getCapDepDist(){
        return this.capDepDist;
    }
}
