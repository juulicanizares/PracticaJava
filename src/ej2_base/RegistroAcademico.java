package ej2_base;

import java.util.Scanner;

public class RegistroAcademico {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = leerEnteroPositivo(sc, "¿Cuántos estudiantes desea registrar? ");
        Estudiante[] estudiantes = new Estudiante[n];

        // ---------- Pedir los datos ----------
        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Estudiante " + (i + 1) + " ---");
            System.out.print("Nombre: ");
            String nombre = sc.nextLine().trim();

            double[] notas = new double[3];
            for (int j = 0; j < 3; j++) {
                notas[j] = leerNota(sc, "Nota " + (j + 1) + " (0 a 10): ");
            }
            estudiantes[i] = new Estudiante(nombre, notas);
        }

        // ---------- Calcular estadísticas ----------
        double sumaPromedios = 0;
        int aprobados = 0;
        int reprobados = 0;
        Estudiante mejor = estudiantes[0];
        Estudiante peor = estudiantes[0];

        for (Estudiante e : estudiantes) {
            sumaPromedios += e.getPromedio();
            if (e.aprobo()) {
                aprobados++;
            } else {
                reprobados++;
            }
            if (e.getPromedio() > mejor.getPromedio()) {
                mejor = e;
            }
            if (e.getPromedio() < peor.getPromedio()) {
                peor = e;
            }
        }

        // ---------- Mostrar resultados ----------
        System.out.println("\n========== RESULTADOS ==========");
        for (Estudiante e : estudiantes) {
            System.out.printf("%-15s promedio: %.2f  -> %s%n",
                    e.getNombre(), e.getPromedio(), e.aprobo() ? "APROBÓ" : "REPROBÓ");
        }
        System.out.printf("%nPromedio general del grupo: %.2f%n", sumaPromedios / n);
        System.out.println("Aprobados: " + aprobados + " | Reprobados: " + reprobados);
        System.out.printf("Mayor promedio: %s (%.2f)%n", mejor.getNombre(), mejor.getPromedio());
        System.out.printf("Menor promedio: %s (%.2f)%n", peor.getNombre(), peor.getPromedio());
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
                System.out.println("Debe ser un número mayor que 0.");
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
