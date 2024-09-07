public class Productor extends Thread{
    private int id; 
    private String tipo;
    private int numProductos;
    private DepositoProduccion deposito;


    public Productor(int id, String tipo, int numProductos, DepositoProduccion deposito){
        this.id = id;
        this.tipo = tipo;
        this.numProductos = numProductos;
        this.deposito = deposito;
    }

    @Override
    public void run(){

        while (numProductos > 1){
            Producto p = new Producto(this.tipo);
            numProductos --;
            deposito.guardarProducto(p);
        }

        

    }
}
