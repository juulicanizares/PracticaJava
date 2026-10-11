package ej1_expandido;

import java.util.ArrayList;

/** El banco guarda todas las cuentas y sabe buscarlas. */
public class Banco {

    private String nombre;
    private ArrayList<CuentaBancaria> cuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    // Devuelve la cuenta o null si no existe
    public CuentaBancaria buscarCuenta(String titular) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getTitular().equalsIgnoreCase(titular)) {
                return cuenta;
            }
        }
        return null;
    }

    public boolean validarPin(String titular, String pin) {
        CuentaBancaria cuenta = buscarCuenta(titular);
        return cuenta != null && cuenta.verificarPin(pin);
    }
}
