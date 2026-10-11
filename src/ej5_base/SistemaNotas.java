package ej5_base;

import java.util.Scanner;

public class SistemaNotas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int PUNTOS_CURVA = 2;

        int n = leerEnteroPositivo(sc, "¿Cuántos alumnos desea ingresar? ");
        Alumno[] alumnos = new Alumno[n];

        for (int i = 0; i < n; i++) {
            System.out.print("\nNombre del alumno " + (i + 1) + ": ");
            String nombre = sc.nextLine().trim();
            double nota = leerNota(sc, "Nota original (0 a 10): ");
            alumnos[i] = new Alumno(nombre, nota);
        }

        // Promedio del curso con las notas originales
        double suma = 0;
        for (Alumno a : alumnos) {
            suma += a.getNotaOriginal();
        }
        double promedio = suma / n;
        System.out.printf("%nPromedio del curso: %.2f%n", promedio);

        // Si el curso salió mal (promedio < 6), se aplica la curva a todos
        if (promedio < 6) {
            System.out.println("El promedio es menor a 6: se aplica una curva de +" + PUNTOS_CURVA + " puntos.");
            for (Alumno a : alumnos) {
                a.aplicarCurva(PUNTOS_CURVA);
            }
        } else {
            System.out.println("El promedio es suficiente: no se aplica curva.");
        }

        System.out.println("\n========== LISTADO ==========");
        for (Alumno a : alumnos) {
            System.out.printf("%-15s original: %.2f | final: %.2f | %s%n",
                    a.getNombre(), a.getNotaOriginal(), a.getNotaFinal(),
                    a.estaAprobado() ? "APROBÓ" : "REPROBÓ");
        }
        sc.close();
    }

    private static int leerEnteroPositivo(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int numero = Integer.parseInt(sc.nextLine().trim());
                if (numero > 0) {
                    return numero;
                }
                System.out.println("Debe ser mayor que 0.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero.");
            }
        }
    }

    private static double leerNota(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double nota = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                if (nota >= 0 && nota <= 10) {
                    return nota;
                }
                System.out.println("La nota debe estar entre 0 y 10.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
    }
}
