package ej3_expandido;

import java.util.ArrayList;

/** Único responsable del catálogo y del stock (Expansión 15). */
public class Inventario {

    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public ArrayList<Producto> getProductos() {
        return new ArrayList<>(productos);
    }

    // Expansión 11: lista de categorías sin repetir
    public ArrayList<String> getCategorias() {
        ArrayList<String> categorias = new ArrayList<>();
        for (Producto p : productos) {
            if (!categorias.contains(p.getCategoria())) {
                categorias.add(p.getCategoria());
            }
        }
        return categorias;
    }

    public ArrayList<Producto> filtrarPorCategoria(String categoria) {
        ArrayList<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public boolean hayStock(Producto producto, int cantidad) {
        return producto.hayStock(cantidad);
    }

    public void reducirStock(Producto producto, int cantidad) {
        producto.disminuirStock(cantidad);
    }
}
