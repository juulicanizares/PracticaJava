package RegistroAcadémicoConEstadísticas;

public class Estudiante {

    private String nombre;
    private double[] notas;   
    private double promedio;

    public Estudiante(String nombre, double[] notasIniciales) {
        this.nombre = nombre;
        this.notas = new double[3];
        for (int i = 0; i < 3; i++) {
            this.notas[i] = notasIniciales[i];
        }
        calcularPromedio(); 
    }

    public void calcularPromedio() {
        double suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }
        promedio = suma / notas.length;
    }

    public boolean aprobo() {
        return promedio >= 6;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPromedio() {
        return promedio;
    }
}
