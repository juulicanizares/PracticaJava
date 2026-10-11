package ej3_base;

/** Una línea del carrito: un producto y cuántas unidades se compraron. */
public class ItemCarrito {

    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void aumentarCantidad(int extra) {
        cantidad += extra;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
