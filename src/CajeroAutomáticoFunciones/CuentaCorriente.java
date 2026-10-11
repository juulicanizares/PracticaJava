package CajeroAutomáticoFunciones;

public class CuentaCorriente extends CuentaBancaria {

    private double limiteSobregiro;

    public CuentaCorriente(String titular, double saldoInicial, String pin, double limiteSobregiro) {
        super(titular, saldoInicial, pin);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public String getTipo() {
        return "Cuenta corriente (sobregiro hasta $" + formato(limiteSobregiro) + ")";
    }

    @Override
    public boolean puedeRetirar(double monto) {
        return monto > 0 && monto <= getSaldo() + limiteSobregiro;
    }
}
