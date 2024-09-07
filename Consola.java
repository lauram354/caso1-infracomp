import java.util.Scanner;
public class Consola {
    private static DepositoProduccion depProduccion;
    private static DespositoDistribucion depDistribucion;
    private static Cinta cinta = new Cinta(false);
    
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
        depDistribucion = new DespositoDistribucion(capDepDist);


    }
}
