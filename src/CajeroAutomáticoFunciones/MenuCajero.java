package CajeroAutomáticoFunciones;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuCajero {

    private static final int MAX_INTENTOS = 3;

    public void iniciar() {
        Scanner sc = new Scanner(System.in);

        Banco banco = new Banco("Banco Junior");
        banco.agregarCuenta(new CuentaAhorro("Ana", 1000, "1234"));
        banco.agregarCuenta(new CuentaCorriente("Luis", 500, "4321", 300));
        banco.agregarCuenta(new CuentaBancaria("Sofia", 200, "1111"));

        boolean encendido = true;
        while (encendido) {
            System.out.println("\n=== " + banco.getNombre() + " ===");
            System.out.print("Ingrese el titular (o escriba SALIR para apagar el cajero): ");
            String titular = sc.nextLine().trim();

            if (titular.equalsIgnoreCase("SALIR")) {
                encendido = false;
                continue;
            }

            CuentaBancaria cuenta = banco.buscarCuenta(titular);
            if (cuenta == null) {
                System.out.println("No existe una cuenta con ese titular.");
            } else if (cuenta.isBloqueada()) {
                System.out.println("Esta cuenta está bloqueada. Comuníquese con su banco.");
            } else if (pedirPin(sc, banco, titular)) {
                menuCajero(sc, banco, cuenta);
            } else {
                cuenta.bloquear();
                System.out.println("Demasiados intentos fallidos. Cuenta bloqueada.");
            }
        }
        System.out.println("Cajero apagado.");
        sc.close();
    }

    private static boolean pedirPin(Scanner sc, Banco banco, String titular) {
        for (int intento = 1; intento <= MAX_INTENTOS; intento++) {
            System.out.print("Ingrese su PIN (intento " + intento + " de " + MAX_INTENTOS + "): ");
            String pin = sc.nextLine().trim();
            if (banco.validarPin(titular, pin)) {
                return true;
            }
            System.out.println("PIN incorrecto.");
        }
        return false;
    }

    private static void menuCajero(Scanner sc, Banco banco, CuentaBancaria cuenta) {
        System.out.println("\nBienvenido/a, " + cuenta.getTitular() + " - " + cuenta.getTipo());
        int opcion = 0;
        while (opcion != 6) {
            System.out.println("\n----- MENÚ -----");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Transferir");
            System.out.println("5. Consultar movimientos");
            System.out.println("6. Salir");
            opcion = leerEntero(sc, "Elija una opción: ");

            switch (opcion) {
                case 1:
                    System.out.printf("Saldo actual: $%.2f%n", cuenta.consultarSaldo());
                    break;
                case 2: {
                    double monto = leerMonto(sc, "Monto a retirar: ");
                    if (cuenta.retirar(monto)) {
                        System.out.println("Retiro exitoso.");
                    } else {
                        System.out.println("No se pudo retirar: monto inválido o fondos insuficientes.");
                    }
                    break;
                }
                case 3: {
                    double monto = leerMonto(sc, "Monto a depositar: ");
                    if (cuenta.depositar(monto)) {
                        System.out.println("Depósito exitoso.");
                    } else {
                        System.out.println("El monto debe ser mayor que 0.");
                    }
                    break;
                }
                case 4:
                    hacerTransferencia(sc, banco, cuenta);
                    break;
                case 5:
                    mostrarMovimientos(cuenta);
                    break;
                case 6:
                    System.out.println("Sesión cerrada.");
                    break;
                default:
                    System.out.println("Opción inválida. Elija un número del 1 al 6.");
            }
        }
    }

    private static void hacerTransferencia(Scanner sc, Banco banco, CuentaBancaria origen) {
        System.out.print("Titular de la cuenta destino: ");
        String nombreDestino = sc.nextLine().trim();
        CuentaBancaria destino = banco.buscarCuenta(nombreDestino);

        if (destino == null) {
            System.out.println("La cuenta destino no existe.");
        } else if (destino == origen) {
            System.out.println("No puede transferirse a su propia cuenta.");
        } else {
            double monto = leerMonto(sc, "Monto a transferir: ");
            if (origen.transferir(destino, monto)) {
                System.out.println("Transferencia exitosa a " + destino.getTitular() + ".");
            } else {
                System.out.println("No se pudo transferir: monto inválido o fondos insuficientes.");
            }
        }
    }

    private static void mostrarMovimientos(CuentaBancaria cuenta) {
        ArrayList<String> movimientos = cuenta.getMovimientos();
        if (movimientos.isEmpty()) {
            System.out.println("Todavía no hay movimientos.");
        } else {
            System.out.println("Últimos movimientos:");
            for (String movimiento : movimientos) {
                System.out.println(" - " + movimiento);
            }
        }
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero.");
            }
        }
    }

    private static double leerMonto(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }
}
