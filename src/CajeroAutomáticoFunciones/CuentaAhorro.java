package CajeroAutomáticoFunciones;

public class CuentaAhorro extends CuentaBancaria {

    private static final double TASA_INTERES = 0.01; 

    public CuentaAhorro(String titular, double saldoInicial, String pin) {
        super(titular, saldoInicial, pin);
    }

    @Override
    public String getTipo() {
        return "Cuenta de ahorro";
    }

    @Override
    public boolean depositar(double monto) {
        if (monto <= 0) {
            return false;
        }
        double interes = monto * TASA_INTERES;
        aumentarSaldo(monto + interes);
        registrarMovimiento("Depósito: +$" + formato(monto) + " (interés: +$" + formato(interes) + ")");
        return true;
    }
}
