package SimulaciónDeTiendaOnlineFunciones;

import java.util.ArrayList;

public class Carrito {

    private static final double MONTO_PARA_DESCUENTO = 1000;
    private static final double PORCENTAJE_DESCUENTO = 0.10; 

    private Cliente cliente;       
    private Inventario inventario; 
    private ArrayList<ItemCarrito> items = new ArrayList<>();

    public Carrito(Cliente cliente, Inventario inventario) {
        this.cliente = cliente;
        this.inventario = inventario;
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (!inventario.hayStock(producto, cantidad)) {
            return false;
        }
        inventario.reducirStock(producto, cantidad);

        for (ItemCarrito item : items) {
            if (item.getProducto() == producto) {
                item.aumentarCantidad(cantidad);
                return true;
            }
        }
        items.add(new ItemCarrito(producto, cantidad));
        return true;
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public double calcularDescuento() {
        double subtotal = calcularSubtotal();
        if (subtotal > MONTO_PARA_DESCUENTO) {
            return subtotal * PORCENTAJE_DESCUENTO;
        }
        return 0;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }

    public void mostrarFactura() {
        System.out.println("\n================ FACTURA ================");
        System.out.println("Cliente: " + cliente.getNombre() + " (" + cliente.getEmail() + ")");
        System.out.println("-----------------------------------------");
        for (ItemCarrito item : items) {
            System.out.printf("%-14s %3d x $%8.2f = $%9.2f%n",
                    item.getProducto().getNombre(), item.getCantidad(),
                    item.getProducto().getPrecio(), item.getSubtotal());
        }
        System.out.println("-----------------------------------------");
        System.out.printf("Subtotal:  $%.2f%n", calcularSubtotal());
        System.out.printf("Descuento: -$%.2f%n", calcularDescuento());
        System.out.printf("TOTAL A PAGAR: $%.2f%n", calcularTotal());
    }
}
