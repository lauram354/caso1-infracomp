import java.util.Scanner;
public class Consola {
    private static DepositoProduccion depProduccion;
    private static DepositoDistribucion depDistribucion;
    private static Cinta cinta = new Cinta(false);  
    
    public static DepositoProduccion getDepProduccion() {
        return depProduccion;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Bienvenido, por favor ingresa el número de productos a realizar: ");
        int numProductos = input.nextInt();
        System.out.println("Ingresa la capacitdad del deposito de producción: ");
        int capDepProd = input.nextInt();
        System.out.println("Ingresa la capacitdad del deposito de distribución: ");
        int capDepDist = input.nextInt();
        input.close();

        depProduccion = new DepositoProduccion(capDepProd);
        depDistribucion = new DepositoDistribucion(capDepDist);

        int productosPorProductor = numProductos/4;
        Productor pTipoA1 = new Productor(1, "A", productosPorProductor, depProduccion);
        Productor pTipoA2 = new Productor(2, "A", productosPorProductor, depProduccion);
        Productor pTipoB1 = new Productor(3, "B", productosPorProductor, depProduccion);
        Productor pTipoB2 = new Productor(4, "B", productosPorProductor, depProduccion);
        OperarioInterno op1 = new OperarioInterno(1, cinta, depProduccion, depDistribucion);

        pTipoA1.start();
        pTipoA2.start();
        pTipoB1.start();
        pTipoB2.start();
        op1.start();


    }
}
