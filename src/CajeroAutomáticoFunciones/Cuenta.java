package CajeroAutomáticoFunciones;

import java.util.ArrayList;

public abstract class Cuenta {

    private static final int MAX_MOVIMIENTOS = 5;

    private String titular;
    private double saldo;
    private String pin;
    private boolean bloqueada;
    private ArrayList<String> movimientos; 

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

    public ArrayList<String> getMovimientos() {
        return new ArrayList<>(movimientos);
    }

    protected void aumentarSaldo(double monto) {
        saldo += monto;
    }

    protected void disminuirSaldo(double monto) {
        saldo -= monto;
    }

    protected void registrarMovimiento(String texto) {
        movimientos.add(texto);
        if (movimientos.size() > MAX_MOVIMIENTOS) {
            movimientos.remove(0); 
        }
    }

    protected String formato(double monto) {
        return String.format("%.2f", monto);
    }

    public abstract String getTipo();
}
