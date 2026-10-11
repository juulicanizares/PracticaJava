package ej5_base;

public class Alumno {

    private String nombre;
    private double notaOriginal;
    private double notaFinal;

    public Alumno(String nombre, double notaOriginal) {
        this.nombre = nombre;
        this.notaOriginal = notaOriginal;
        this.notaFinal = notaOriginal; // al inicio, la final es igual a la original
    }

    // Suma los puntos de la curva, pero la nota nunca pasa de 10
    public void aplicarCurva(int puntos) {
        notaFinal = Math.min(10, notaOriginal + puntos);
    }

    public boolean estaAprobado() {
        return notaFinal >= 6;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotaOriginal() {
        return notaOriginal;
    }

    public double getNotaFinal() {
        return notaFinal;
    }
}
