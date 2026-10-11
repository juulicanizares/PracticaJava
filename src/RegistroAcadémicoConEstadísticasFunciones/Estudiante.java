package RegistroAcadémicoConEstadísticasFunciones;

import java.util.ArrayList;

public class Estudiante {

    private static final double NOTA_MINIMA_APROBAR = 6;
    private static final int FALTAS_MAXIMAS = 10; 

    private String nombre;
    private ArrayList<Asignatura> asignaturas; 
    private int faltas;                         

    public Estudiante(String nombre, int faltas) {
        this.nombre = nombre;
        this.faltas = faltas;
        this.asignaturas = new ArrayList<>();
    }

    public void agregarAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }

    public Asignatura buscarAsignatura(String nombreAsignatura) {
        for (Asignatura a : asignaturas) {
            if (a.getNombre().equalsIgnoreCase(nombreAsignatura)) {
                return a;
            }
        }
        return null;
    }

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
