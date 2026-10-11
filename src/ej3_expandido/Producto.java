package ej3_expandido;

/** Clase base. Las clases hijas (alimenticio, electrónico) heredan todo esto. */
public class Producto {

    private String nombre;
    private String categoria; // Expansión 11
    private double precio;
    private int stock;

    public Producto(String nombre, String categoria, double precio, int stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
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

    // Las hijas lo sobrescriben para mostrar su dato especial
    public String getDetalleExtra() {
        return "";
    }
}
