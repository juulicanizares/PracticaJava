package ej1_base;

/**
 * Representa una cuenta bancaria sencilla.
 * Los atributos son privados (encapsulamiento): solo se pueden
 * tocar a través de los métodos de esta clase.
 */
public class CuentaBancaria {

    private String titular;
    private double saldo;
    private String pin;

    public CuentaBancaria(String titular, double saldoInicial, String pin) {
        this.titular = titular;
        this.saldo = saldoInicial;
        this.pin = pin;
    }

    public String getTitular() {
        return titular;
    }

    // El PIN nunca se devuelve: solo se pregunta "¿este PIN es el correcto?"
    public boolean verificarPin(String pinIngresado) {
        return pin.equals(pinIngresado);
    }

    public double consultarSaldo() {
        return saldo;
    }

    // Devuelve true si el depósito se pudo hacer, false si el monto no es válido
    public boolean depositar(double monto) {
        if (monto <= 0) {
            return false;
        }
        saldo += monto;
        return true;
    }

    // Devuelve true si el retiro se pudo hacer, false si el monto es inválido o no alcanza el saldo
    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) {
            return false;
        }
        saldo -= monto;
        return true;
    }
}
