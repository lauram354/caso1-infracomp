public class OperarioInterno extends Thread{
    private int id;
    private Cinta cinta;
    private DepositoProduccion depositoProduccion;
    private DepositoDistribucion depositoDistribucion;
    private int termina = 0;
    private int termina2 = 0;

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
            while (termina <4){
                while (!depositoProduccion.hayProductos() | cinta.getOcupado()){
                    Thread.yield();
                }

                Producto p = depositoProduccion.sacarProducto();
                if (p.getTipo().equals("FIN_A") || p.getTipo().equals("FIN_B") ){
                    termina ++;
                }
                cinta.guardarProducto(p);
            }
            
        }else if (id == 2){
            while (termina2 <4){
                while (!cinta.getOcupado() || depositoDistribucion.getCapDepDist() == 0){
                    Thread.yield();
                }
                
                Producto p = cinta.sacaProducto();
                if (p.getTipo().equals("FIN_A") || p.getTipo().equals("FIN_B") ){
                    termina2 ++;
                }
                depositoDistribucion.guardarProducto(p);
            }          

        }

        System.out.println("Termino el operario " + String.valueOf(id));
    }

    
    
}
