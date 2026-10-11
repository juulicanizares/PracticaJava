package ej3_expandido;

import java.time.LocalDate;

public class ProductoAlimenticio extends Producto {

    private LocalDate fechaCaducidad;

    public ProductoAlimenticio(String nombre, String categoria, double precio, int stock, LocalDate fechaCaducidad) {
        super(nombre, categoria, precio, stock);
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String getDetalleExtra() {
        return "Vence: " + fechaCaducidad;
    }
}
