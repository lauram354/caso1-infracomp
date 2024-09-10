public class Producto {
    private String tipo;
    private int idProductorEncargado;

    public Producto(String tipo, int idProductorEncargado) {
        this.tipo = tipo;
        this.idProductorEncargado = idProductorEncargado;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIdProductorEncargado() {
        return idProductorEncargado;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
