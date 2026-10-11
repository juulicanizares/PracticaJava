package ej1_expandido;

/**
 * HEREDA de Cuenta ("extends"): ya tiene titular, saldo, pin e historial.
 * Aquí se agregan las operaciones: depositar, retirar, transferir.
 */
public class CuentaBancaria extends Cuenta {

    public CuentaBancaria(String titular, double saldoInicial, String pin) {
        super(titular, saldoInicial, pin); // llama al constructor de Cuenta
    }

    @Override
    public String getTipo() {
        return "Cuenta bancaria";
    }

    public double consultarSaldo() {
        return getSaldo();
    }

    // Las hijas pueden cambiar esta regla (por ejemplo, CuentaCorriente con sobregiro)
    public boolean puedeRetirar(double monto) {
        return monto > 0 && monto <= getSaldo();
    }

    public boolean depositar(double monto) {
        if (monto <= 0) {
            return false;
        }
        aumentarSaldo(monto);
        registrarMovimiento("Depósito: +$" + formato(monto));
        return true;
    }

    public boolean retirar(double monto) {
        if (!puedeRetirar(monto)) {
            return false;
        }
        disminuirSaldo(monto);
        registrarMovimiento("Retiro: -$" + formato(monto));
        return true;
    }

    // Expansión 1: transferencia entre dos cuentas
    public boolean transferir(CuentaBancaria cuentaDestino, double monto) {
        if (cuentaDestino == this || !puedeRetirar(monto)) {
            return false;
        }
        disminuirSaldo(monto);
        registrarMovimiento("Transferencia a " + cuentaDestino.getTitular() + ": -$" + formato(monto));
        cuentaDestino.recibirTransferencia(getTitular(), monto);
        return true;
    }

    protected void recibirTransferencia(String origen, double monto) {
        aumentarSaldo(monto);
        registrarMovimiento("Transferencia de " + origen + ": +$" + formato(monto));
    }
}
