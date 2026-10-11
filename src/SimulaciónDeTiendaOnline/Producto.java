package SimulaciónDeTiendaOnline;

public class Producto {

    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public boolean hayStock(int cantidad) {
        return cantidad > 0 && stock >= cantidad;
    }

    public void disminuirStock(int cantidad) {
        stock -= cantidad;
    }
}
