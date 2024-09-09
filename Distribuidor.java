public class Distribuidor extends Thread {

    DepositoDistribucion depositoDistribucion;
    private int id;
    private String tipo;

    public Distribuidor(int id, String tipo, DepositoDistribucion depositoDistribucion) {
        this.id = id;
        this.tipo = tipo;
        this.depositoDistribucion = depositoDistribucion;
    }

    @Override
    public void run() {
        while (true) {
            Producto p = depositoDistribucion.sacarProducto(this.tipo);
            if (p != null) {
                System.out.println("El distribuidor " + this.id + " saco un producto tipo " + p.getTipo());
            }
        }
    }

}
