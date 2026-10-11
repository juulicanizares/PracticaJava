package SimulaciónDeTiendaOnlineFunciones;

public class ProductoElectronico extends Producto {

    private int mesesGarantia;

    public ProductoElectronico(String nombre, String categoria, double precio, int stock, int mesesGarantia) {
        super(nombre, categoria, precio, stock);
        this.mesesGarantia = mesesGarantia;
    }

    @Override
    public String getDetalleExtra() {
        return "Garantía: " + mesesGarantia + " meses";
    }
}
