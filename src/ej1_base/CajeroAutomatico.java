package ej1_base;

import java.util.Scanner;

public class CajeroAutomatico {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CuentaBancaria cuenta = new CuentaBancaria("Ana Gómez", 1000.00, "1234");

        // ---------- 1. Validar el PIN (máximo 3 intentos) ----------
        final int MAX_INTENTOS = 3;
        int intentos = 0;
        boolean accesoPermitido = false;

        while (intentos < MAX_INTENTOS && !accesoPermitido) {
            System.out.print("Ingrese su PIN: ");
            String pin = sc.nextLine().trim();
            if (cuenta.verificarPin(pin)) {
                accesoPermitido = true;
            } else {
                intentos++;
                System.out.println("PIN incorrecto. Intentos restantes: " + (MAX_INTENTOS - intentos));
            }
        }

        if (!accesoPermitido) {
            System.out.println("Cuenta bloqueada. Comuníquese con su banco.");
            return; // termina el programa
        }

        // ---------- 2. Menú que se repite hasta elegir "Salir" ----------
        System.out.println("Bienvenido/a, " + cuenta.getTitular());
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("\n===== CAJERO AUTOMÁTICO =====");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Salir");
            opcion = leerEntero(sc, "Elija una opción: ");

            switch (opcion) {
                case 1: {
                    System.out.printf("Su saldo actual es: $%.2f%n", cuenta.consultarSaldo());
                    break;
                }
                case 2: {
                    double monto = leerMonto(sc, "Monto a retirar: ");
                    if (monto <= 0) {
                        System.out.println("El monto debe ser mayor que 0.");
                    } else if (monto > cuenta.consultarSaldo()) {
                        System.out.println("Fondos insuficientes.");
                    } else {
                        cuenta.retirar(monto);
                        System.out.println("Retiro exitoso.");
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
                    System.out.println("Gracias por usar el cajero. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción inválida. Elija un número del 1 al 4.");
            }
        }
        sc.close();
    }

    // Lee una línea y la convierte a int. Si el usuario escribe letras, vuelve a pedir.
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

    // Igual que el anterior, pero para decimales (acepta "10.5" y "10,5")
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
