package SimulaciónDeTiendaOnline;

import java.util.ArrayList;

public class Carrito {

    private ArrayList<ItemCarrito> items = new ArrayList<>();

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (!producto.hayStock(cantidad)) {
            return false;
        }
        producto.disminuirStock(cantidad);

        for (ItemCarrito item : items) {
            if (item.getProducto() == producto) {
                item.aumentarCantidad(cantidad);
                return true;
            }
        }
        items.add(new ItemCarrito(producto, cantidad));
        return true;
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public void mostrarFactura() {
        System.out.println("\n============== FACTURA ==============");
        for (ItemCarrito item : items) {
            System.out.printf("%-14s %3d x $%8.2f = $%9.2f%n",
                    item.getProducto().getNombre(), item.getCantidad(),
                    item.getProducto().getPrecio(), item.getSubtotal());
        }
        System.out.println("-------------------------------------");
        System.out.printf("TOTAL A PAGAR: $%.2f%n", calcularTotal());
    }
}
