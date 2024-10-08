import java.util.List;
import java.util.ArrayList;

public class Cinta {
    private Boolean ocupado;
    private List<Producto> productos = new ArrayList<>();

    public Cinta(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    public synchronized Boolean getOcupado() {
        return ocupado;
    }

    public synchronized void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    public synchronized void guardarProducto(Producto p) {
        this.productos.add(p);
        this.ocupado = true;
    }

    public synchronized Producto sacaProducto() {
        Producto p = this.productos.get(0);
        this.productos.remove(0);
        this.ocupado = false;
        return p;
    }
}
