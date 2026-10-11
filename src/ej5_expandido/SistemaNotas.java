package ej5_expandido;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class SistemaNotas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Con Locale.US los decimales se escriben con PUNTO (7.5), sin importar el idioma del PC
        sc.useLocale(Locale.US);

        int n = leerEnteroPositivo(sc, "¿Cuántos alumnos desea ingresar? ");
        Alumno[] alumnos = new Alumno[n];

        for (int i = 0; i < n; i++) {
            System.out.print("\nNombre del alumno " + (i + 1) + ": ");
            String nombre = sc.nextLine().trim();
            double parcial1 = leerNota(sc, "Nota del parcial 1 (30%): ");
            double parcial2 = leerNota(sc, "Nota del parcial 2 (30%): ");
            double examen = leerNota(sc, "Nota del examen final (40%): ");
            alumnos[i] = new Alumno(nombre, parcial1, parcial2, examen);
        }

        // ---------- Promedio original ----------
        double promedioOriginal = calcularPromedioOriginal(alumnos);
        System.out.printf("%nPromedio original del curso: %.2f%n", promedioOriginal);

        // ---------- Curva (solo si el promedio es menor a 6) ----------
        if (promedioOriginal < 6) {
            System.out.println("El promedio es menor a 6. Elija el tipo de curva:");
            System.out.println("1. Curva porcentual");
            System.out.println("2. Curva basada en el mejor alumno");
            int tipo = 0;
            while (tipo != 1 && tipo != 2) {
                tipo = leerEnteroPositivo(sc, "Opción (1 o 2): ");
            }

            if (tipo == 1) {
                double porcentaje = leerPorcentaje(sc, "Porcentaje de curva (ej. 10): ");
                for (Alumno a : alumnos) {
                    a.aplicarCurvaPorcentual(porcentaje);
                }
                System.out.println("Se aplicó una curva de " + porcentaje + "%.");
            } else {
                double mejorNota = buscarMejorNotaOriginal(alumnos);
                double puntos = 10 - mejorNota;
                for (Alumno a : alumnos) {
                    a.aplicarCurvaPuntos(puntos);
                }
                System.out.printf("Mejor nota: %.2f -> se suman +%.2f puntos a todos.%n", mejorNota, puntos);
            }
        } else {
            System.out.println("El promedio es suficiente: no se aplica curva.");
        }

        // ---------- Listado ----------
        System.out.println("\n========== LISTADO ==========");
        for (Alumno a : alumnos) {
            System.out.printf("%-15s original: %.2f | final: %.2f | %s%n",
                    a.getNombre(), a.getNotaOriginal(), a.getNotaFinal(),
                    a.estaAprobado() ? "APROBÓ" : "REPROBÓ");
        }

        // ---------- Expansión 25: estadísticas avanzadas ----------
        double nuevaMedia = calcularMediaFinal(alumnos);
        double desviacion = calcularDesviacionEstandar(alumnos, nuevaMedia);
        int aprobadosPorCurva = 0;
        for (Alumno a : alumnos) {
            if (a.aprobadoGraciasALaCurva()) {
                aprobadosPorCurva++;
            }
        }
        System.out.println("\n----- Estadísticas -----");
        System.out.printf("Nueva media del curso: %.2f%n", nuevaMedia);
        System.out.printf("Desviación estándar de las notas finales: %.2f%n", desviacion);
        System.out.println("Alumnos que pasaron de reprobado a aprobado por la curva: " + aprobadosPorCurva);
        sc.close();
    }

    // ---------- Métodos de cálculo ----------
    private static double calcularPromedioOriginal(Alumno[] alumnos) {
        double suma = 0;
        for (Alumno a : alumnos) {
            suma += a.getNotaOriginal();
        }
        return suma / alumnos.length;
    }

    private static double calcularMediaFinal(Alumno[] alumnos) {
        double suma = 0;
        for (Alumno a : alumnos) {
            suma += a.getNotaFinal();
        }
        return suma / alumnos.length;
    }

    private static double buscarMejorNotaOriginal(Alumno[] alumnos) {
        double mejor = alumnos[0].getNotaOriginal();
        for (Alumno a : alumnos) {
            if (a.getNotaOriginal() > mejor) {
                mejor = a.getNotaOriginal();
            }
        }
        return mejor;
    }

    // Desviación estándar poblacional: raíz del promedio de (nota - media)²
    private static double calcularDesviacionEstandar(Alumno[] alumnos, double media) {
        double sumaCuadrados = 0;
        for (Alumno a : alumnos) {
            sumaCuadrados += Math.pow(a.getNotaFinal() - media, 2);
        }
        return Math.sqrt(sumaCuadrados / alumnos.length);
    }

    // ---------- Expansión 24: lectura con try-catch de InputMismatchException ----------
    private static double leerNota(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double nota = sc.nextDouble();
                sc.nextLine(); // consume el Enter que quedó pendiente
                if (nota >= 0 && nota <= 10) {
                    return nota;
                }
                System.out.println("La nota debe estar entre 0 y 10.");
            } catch (InputMismatchException e) {
                System.out.println("Error: debe escribir un número (use punto para decimales).");
                sc.nextLine(); // descarta el texto incorrecto para poder volver a pedir
            }
        }
    }

    private static double leerPorcentaje(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double porcentaje = sc.nextDouble();
                sc.nextLine();
                if (porcentaje > 0 && porcentaje <= 100) {
                    return porcentaje;
                }
                System.out.println("El porcentaje debe estar entre 0 y 100.");
            } catch (InputMismatchException e) {
                System.out.println("Error: debe escribir un número.");
                sc.nextLine();
            }
        }
    }

    private static int leerEnteroPositivo(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int numero = sc.nextInt();
                sc.nextLine();
                if (numero > 0) {
                    return numero;
                }
                System.out.println("Debe ser mayor que 0.");
            } catch (InputMismatchException e) {
                System.out.println("Error: debe escribir un número entero.");
                sc.nextLine();
            }
        }
    }
}
