public class Productor extends Thread {
    private int id;
    private String tipo;
    private int numProductos;
    private DepositoProduccion deposito;

    public Productor(int id, String tipo, int numProductos, DepositoProduccion deposito) {
        this.id = id;
        this.tipo = tipo;
        this.numProductos = numProductos;
        this.deposito = deposito;
    }

    @Override
    public void run() {

        while (numProductos > 0) {
            Producto p = new Producto(this.tipo);
            numProductos--;
            deposito.guardarProducto(p);
            System.out.println("Productor " + String.valueOf(id) + ": produjo un producto " + p.getTipo()
                    + ". Restantes: " + String.valueOf(numProductos));
        }
        Producto terminal = new Producto("FIN_" + this.tipo);
        System.out.println("Productor " + String.valueOf(id) + ": produjo un producto terminal " + terminal.getTipo());
        deposito.agregarProductoTerminal(terminal);
        System.out.println("Termino el productor " + String.valueOf(id));

    }
}
