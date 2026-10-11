package ej5_expandido;

public class Alumno {

    // Expansión 23: ponderaciones
    private static final double PESO_PARCIAL_1 = 0.30;
    private static final double PESO_PARCIAL_2 = 0.30;
    private static final double PESO_EXAMEN = 0.40;

    private String nombre;
    private double notaParcial1;
    private double notaParcial2;
    private double examenFinal;
    private double notaOriginal;
    private double notaFinal;

    public Alumno(String nombre, double notaParcial1, double notaParcial2, double examenFinal) {
        this.nombre = nombre;
        this.notaParcial1 = notaParcial1;
        this.notaParcial2 = notaParcial2;
        this.examenFinal = examenFinal;
        this.notaOriginal = calcularNotaOriginal();
        this.notaFinal = notaOriginal; // sin curva, la final es la original
    }

    public double calcularNotaOriginal() {
        return notaParcial1 * PESO_PARCIAL_1
                + notaParcial2 * PESO_PARCIAL_2
                + examenFinal * PESO_EXAMEN;
    }

    // Expansión 21: curva porcentual. 10 % de 5.0 suma 0.5
    public void aplicarCurvaPorcentual(double porcentaje) {
        double aumento = notaOriginal * porcentaje / 100;
        notaFinal = Math.min(10, notaOriginal + aumento);
    }

    // Expansión 22: curva en puntos (se usa con 10 - mejor nota)
    public void aplicarCurvaPuntos(double puntos) {
        notaFinal = Math.min(10, notaOriginal + puntos);
    }

    public boolean estaAprobado() {
        return notaFinal >= 6;
    }

    // Para la expansión 25: ¿aprobó SOLO gracias a la curva?
    public boolean aprobadoGraciasALaCurva() {
        return notaOriginal < 6 && notaFinal >= 6;
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
