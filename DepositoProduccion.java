import java.util.List;
import java.util.ArrayList;

public class DepositoProduccion {
    private int capDepProd;
    private List<Producto> productos = new ArrayList<>();

    public DepositoProduccion(int capDepProd) {
        this.capDepProd = capDepProd;
    }

    public int getCapDepProd() {
        return capDepProd;
    }

    
}
