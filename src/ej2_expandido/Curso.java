package ej2_expandido;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Curso {

    private static final String SALTO = System.lineSeparator();

    private String nombre;
    private ArrayList<Estudiante> estudiantes;

    public Curso(String nombre) {
        this.nombre = nombre;
        this.estudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public int getCantidadEstudiantes() {
        return estudiantes.size();
    }

    // ---------- Expansión 7: estadísticas dentro de Curso ----------
    public double calcularPromedioGeneral() {
        if (estudiantes.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Estudiante e : estudiantes) {
            suma += e.calcularPromedio();
        }
        return suma / estudiantes.size();
    }

    public int contarAprobados() {
        int contador = 0;
        for (Estudiante e : estudiantes) {
            if (e.aprobo()) {
                contador++;
            }
        }
        return contador;
    }

    public int contarReprobados() {
        return estudiantes.size() - contarAprobados();
    }

    // Devuelve null si el curso está vacío
    public Estudiante getMejorEstudiante() {
        Estudiante mejor = null;
        for (Estudiante e : estudiantes) {
            if (mejor == null || e.calcularPromedio() > mejor.calcularPromedio()) {
                mejor = e;
            }
        }
        return mejor;
    }

    public Estudiante getPeorEstudiante() {
        Estudiante peor = null;
        for (Estudiante e : estudiantes) {
            if (peor == null || e.calcularPromedio() < peor.calcularPromedio()) {
                peor = e;
            }
        }
        return peor;
    }

    // ---------- Expansión 10: búsqueda y modificación ----------
    public Estudiante buscarEstudiante(String nombreEstudiante) {
        for (Estudiante e : estudiantes) {
            if (e.getNombre().equalsIgnoreCase(nombreEstudiante)) {
                return e;
            }
        }
        return null;
    }

    // Devuelve true si pudo cambiar la nota
    public boolean modificarNota(String nombreEstudiante, String nombreAsignatura, double nuevaNota) {
        if (nuevaNota < 0 || nuevaNota > 10) {
            return false;
        }
        Estudiante estudiante = buscarEstudiante(nombreEstudiante);
        if (estudiante == null) {
            return false;
        }
        Asignatura asignatura = estudiante.buscarAsignatura(nombreAsignatura);
        if (asignatura == null) {
            return false;
        }
        asignatura.setNotaFinal(nuevaNota);
        return true;
    }

    // ---------- Reporte (se usa en pantalla y en archivo) ----------
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== REPORTE DEL CURSO: ").append(nombre).append(" =====").append(SALTO);

        if (estudiantes.isEmpty()) {
            sb.append("No hay estudiantes registrados.").append(SALTO);
            return sb.toString();
        }

        for (Estudiante e : estudiantes) {
            sb.append(String.format("%-15s promedio: %.2f | faltas: %d | %s",
                    e.getNombre(), e.calcularPromedio(), e.getFaltas(),
                    e.aprobo() ? "APROBÓ" : "REPROBÓ")).append(SALTO);
        }

        sb.append(SALTO).append("--- Estadísticas generales ---").append(SALTO);
        sb.append(String.format("Promedio general: %.2f", calcularPromedioGeneral())).append(SALTO);
        sb.append("Aprobados: ").append(contarAprobados())
                .append(" | Reprobados: ").append(contarReprobados()).append(SALTO);
        Estudiante mejor = getMejorEstudiante();
        Estudiante peor = getPeorEstudiante();
        sb.append(String.format("Mejor estudiante: %s (%.2f)", mejor.getNombre(), mejor.calcularPromedio())).append(SALTO);
        sb.append(String.format("Peor estudiante: %s (%.2f)", peor.getNombre(), peor.calcularPromedio())).append(SALTO);
        return sb.toString();
    }

    // ---------- Expansión 9: guardar en archivo .txt ----------
    // Devuelve true si se guardó bien
    public boolean guardarReporte(String nombreArchivo) {
        // try-with-resources: cierra el archivo automáticamente al terminar
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo))) {
            escritor.print(generarReporte());
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
            return false;
        }
    }
}
