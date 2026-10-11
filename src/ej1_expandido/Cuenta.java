package ej1_expandido;

import java.util.ArrayList;

/**
 * Clase ABSTRACTA: es una "plantilla" que no se puede instanciar con new.
 * Guarda lo que TODA cuenta tiene en común (titular, saldo, pin, historial).
 */
public abstract class Cuenta {

    private static final int MAX_MOVIMIENTOS = 5;

    private String titular;
    private double saldo;
    private String pin;
    private boolean bloqueada;
    private ArrayList<String> movimientos; // Expansión 2: historial

    public Cuenta(String titular, double saldoInicial, String pin) {
        this.titular = titular;
        this.saldo = saldoInicial;
        this.pin = pin;
        this.bloqueada = false;
        this.movimientos = new ArrayList<>();
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean verificarPin(String pinIngresado) {
        return pin.equals(pinIngresado);
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void bloquear() {
        bloqueada = true;
    }

    // Devuelve una COPIA para que nadie de afuera modifique el historial real
    public ArrayList<String> getMovimientos() {
        return new ArrayList<>(movimientos);
    }

    // "protected": solo las clases hijas pueden usar estos métodos
    protected void aumentarSaldo(double monto) {
        saldo += monto;
    }

    protected void disminuirSaldo(double monto) {
        saldo -= monto;
    }

    // Guarda el movimiento y mantiene solo los últimos 5
    protected void registrarMovimiento(String texto) {
        movimientos.add(texto);
        if (movimientos.size() > MAX_MOVIMIENTOS) {
            movimientos.remove(0); // borra el más antiguo
        }
    }

    protected String formato(double monto) {
        return String.format("%.2f", monto);
    }

    // Método abstracto: cada hija DEBE decir qué tipo de cuenta es
    public abstract String getTipo();
}
