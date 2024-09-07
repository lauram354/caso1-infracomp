public class OperarioInterno<T> extends Thread{
    private int id;
    private Cinta cinta;
    private DepositoProduccion depositoProduccion;
    private DepositoDistribucion depositoDistribucion;

    public OperarioInterno(int id, Cinta cinta, DepositoProduccion depositoProduccion, DepositoDistribucion depositoDistribucion) {
        this.id = id;
        this.cinta = cinta;
        this.depositoProduccion = depositoProduccion;
        this.depositoDistribucion = depositoDistribucion;
    }
    
    @Override
    public void run(){
        /*Si el id es 1 significa que es el operario que lleva del deposito de producción a la cinta */
        if (this.id == 1){
            while (!depositoProduccion.hayProductos()){
                Thread.yield();
            }
            
            depositoProduccion = Consola.getDepProduccion();
            Producto p = depositoProduccion.sacarProducto();
            if (!cinta.getOcupado()){
                cinta.guardarProducto(p);
            }else{
                Thread.yield();
            }
            
        }
    }

    
    
}
