import java.util.Scanner;

public class Consola {
    private static DepositoProduccion depProduccion;
    private static DepositoDistribucion depDistribucion;
    private static Cinta cinta = new Cinta(false);

    public static DepositoProduccion getDepProduccion() {
        return depProduccion;
    }

    public static void main(String[] args) {

        boolean seguir = false;
        int numProductos = 0;
        int capDepProd = 0;
        int capDepDist = 0;

        Scanner input = new Scanner(System.in);
        while (!seguir) {

            System.out.println("Bienvenido, por favor ingresa el número de productos a realizar: ");
            numProductos = input.nextInt();
            System.out.println("Ingresa la capacidad del deposito de producción: ");
            capDepProd = input.nextInt();
            System.out.println("Ingresa la capacidad del deposito de distribución: ");
            capDepDist = input.nextInt();

            if (numProductos < 1 || capDepProd < 1 || capDepDist < 1) {
                System.out.println("Por favor ingresa valores válidos");
            } else {
                seguir = true;
            }
        }
        input.close();

        depProduccion = new DepositoProduccion(capDepProd);
        depDistribucion = new DepositoDistribucion(capDepDist);

        Productor pTipoA1 = new Productor(1, "A", numProductos, depProduccion);
        Productor pTipoA2 = new Productor(2, "A", numProductos, depProduccion);
        Productor pTipoB1 = new Productor(3, "B", numProductos, depProduccion);
        Productor pTipoB2 = new Productor(4, "B", numProductos, depProduccion);
        OperarioInterno op1 = new OperarioInterno(1, cinta, depProduccion, depDistribucion);
        OperarioInterno op2 = new OperarioInterno(2, cinta, depProduccion, depDistribucion);
        Distribuidor dTipoA1 = new Distribuidor(1, "A", depDistribucion);
        Distribuidor dTipoA2 = new Distribuidor(2, "A", depDistribucion);
        Distribuidor dTipoB1 = new Distribuidor(3, "B", depDistribucion);
        Distribuidor dTipoB2 = new Distribuidor(4, "B", depDistribucion);

        pTipoA1.start();
        pTipoA2.start();
        pTipoB1.start();
        pTipoB2.start();
        op1.start();
        op2.start();
        dTipoA1.start();
        dTipoA2.start();
        dTipoB1.start();
        dTipoB2.start();
        /* System.out.println("Termina el main"); */
    }
}
