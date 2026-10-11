package ej2_expandido;

import java.util.Scanner;

public class RegistroAcademico {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Curso curso = new Curso("Programación Java");

        int opcion = 0;
        while (opcion != 6) {
            System.out.println("\n===== REGISTRO ACADÉMICO =====");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Ver reporte del curso");
            System.out.println("3. Buscar estudiante");
            System.out.println("4. Modificar una nota");
            System.out.println("5. Guardar reporte en archivo");
            System.out.println("6. Salir");
            opcion = leerEntero(sc, "Elija una opción: ");

            switch (opcion) {
                case 1:
                    registrarEstudiante(sc, curso);
                    break;
                case 2:
                    System.out.println(curso.generarReporte());
                    break;
                case 3:
                    buscarEstudiante(sc, curso);
                    break;
                case 4:
                    modificarNota(sc, curso);
                    break;
                case 5:
                    System.out.print("Nombre del archivo (ej. reporte.txt): ");
                    String archivo = sc.nextLine().trim();
                    if (curso.guardarReporte(archivo)) {
                        System.out.println("Reporte guardado en " + archivo);
                    }
                    break;
                case 6:
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }

    private static void registrarEstudiante(Scanner sc, Curso curso) {
        System.out.print("Nombre del estudiante: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty() || curso.buscarEstudiante(nombre) != null) {
            System.out.println("El nombre está vacío o ya existe un estudiante con ese nombre.");
            return;
        }
        int faltas = leerEntero(sc, "Número de faltas: ");
        Estudiante estudiante = new Estudiante(nombre, faltas);

        int cantidad = leerEntero(sc, "¿Cuántas asignaturas tiene? ");
        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Nombre de la asignatura " + i + ": ");
            String nombreAsignatura = sc.nextLine().trim();
            double nota = leerNota(sc, "Nota final de " + nombreAsignatura + " (0 a 10): ");
            estudiante.agregarAsignatura(new Asignatura(nombreAsignatura, nota));
        }
        curso.agregarEstudiante(estudiante);
        System.out.println("Estudiante registrado.");
    }

    private static void buscarEstudiante(Scanner sc, Curso curso) {
        System.out.print("Nombre a buscar: ");
        Estudiante e = curso.buscarEstudiante(sc.nextLine().trim());
        if (e == null) {
            System.out.println("No se encontró el estudiante.");
            return;
        }
        System.out.printf("%s | promedio %.2f | faltas %d | %s%n",
                e.getNombre(), e.calcularPromedio(), e.getFaltas(), e.aprobo() ? "APROBÓ" : "REPROBÓ");
        for (Asignatura a : e.getAsignaturas()) {
            System.out.printf("   - %s: %.2f%n", a.getNombre(), a.getNotaFinal());
        }
    }

    private static void modificarNota(Scanner sc, Curso curso) {
        System.out.print("Nombre del estudiante: ");
        String nombreEstudiante = sc.nextLine().trim();
        System.out.print("Nombre de la asignatura: ");
        String nombreAsignatura = sc.nextLine().trim();
        double nuevaNota = leerNota(sc, "Nueva nota (0 a 10): ");

        if (curso.modificarNota(nombreEstudiante, nombreAsignatura, nuevaNota)) {
            System.out.println("Nota modificada.");
        } else {
            System.out.println("No se pudo modificar: revise el estudiante y la asignatura.");
        }
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int numero = Integer.parseInt(sc.nextLine().trim());
                if (numero >= 0) {
                    return numero;
                }
                System.out.println("No puede ser negativo.");
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
