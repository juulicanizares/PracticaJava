package CajeroAutomático;


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

    public boolean verificarPin(String pinIngresado) {
        return pin.equals(pinIngresado);
    }

    public double consultarSaldo() {
        return saldo;
    }

    public boolean depositar(double monto) {
        if (monto <= 0) {
            return false;
        }
        saldo += monto;
        return true;
    }

    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) {
            return false;
        }
        saldo -= monto;
        return true;
    }
}
