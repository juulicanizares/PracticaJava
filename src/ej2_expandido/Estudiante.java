package ej2_expandido;

import java.util.ArrayList;

public class Estudiante {

    private static final double NOTA_MINIMA_APROBAR = 6;
    private static final int FALTAS_MAXIMAS = 10; // con 10 faltas o más, reprueba

    private String nombre;
    private ArrayList<Asignatura> asignaturas; // Expansión 6
    private int faltas;                         // Expansión 8

    public Estudiante(String nombre, int faltas) {
        this.nombre = nombre;
        this.faltas = faltas;
        this.asignaturas = new ArrayList<>();
    }

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }

    // Devuelve la asignatura con ese nombre, o null si no existe
    public Asignatura buscarAsignatura(String nombreAsignatura) {
        for (Asignatura a : asignaturas) {
            if (a.getNombre().equalsIgnoreCase(nombreAsignatura)) {
                return a;
            }
        }
        return null;
    }

    // Se calcula cada vez que se pide, así nunca queda desactualizado
    // (por ejemplo, después de modificar una nota).
    public double calcularPromedio() {
        if (asignaturas.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Asignatura a : asignaturas) {
            suma += a.getNotaFinal();
        }
        return suma / asignaturas.size();
    }

    // Expansión 8: aprueba solo si promedio >= 6 Y faltas < 10
    public boolean aprobo() {
        return calcularPromedio() >= NOTA_MINIMA_APROBAR && faltas < FALTAS_MAXIMAS;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFaltas() {
        return faltas;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return new ArrayList<>(asignaturas);
    }
}
