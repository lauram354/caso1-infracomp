public class Productor extends Thread{
    private int id; 
    private String tipo;
    private int numProductos;

    public void Productor(int id, String tipo, int numProductos){
        this.id = id;
        this.tipo = tipo;
        this.numProductos = numProductos;
    }

    @Override
    public void run(){

    }
}
