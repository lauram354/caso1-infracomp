public class Distribuidor extends Thread {
    private int id;
    private String tipo;
    private DepositoDistribucion deposito;
    private int terminar = 0;

    public Distribuidor(int id, String tipo, DepositoDistribucion deposito) {
        this.id = id;
        this.tipo = tipo;
        this.deposito = deposito;
    }

    @Override
    public void run() {
        while (terminar < 1) {
            Producto p = deposito.sacarProducto(tipo, this.id);
            System.out.println("Distribuidor " + String.valueOf(this.id) + ": tomo un producto " + p.getTipo());
            if (p.getTipo().equals("FIN_" + this.tipo)) {
                terminar++;
            }
        }

    }
}
