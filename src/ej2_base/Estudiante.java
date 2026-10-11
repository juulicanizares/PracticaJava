package ej2_base;

public class Estudiante {

    private String nombre;
    private double[] notas;   // arreglo con las 3 notas
    private double promedio;

    // El constructor recibe el nombre y las notas y las guarda
    public Estudiante(String nombre, double[] notasIniciales) {
        this.nombre = nombre;
        this.notas = new double[3];
        for (int i = 0; i < 3; i++) {
            this.notas[i] = notasIniciales[i];
        }
        calcularPromedio(); // dejamos el promedio listo desde el inicio
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
