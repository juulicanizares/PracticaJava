package SistemaDeNotasConCurvaFunciones;

/**
 * Clase de arranque: el main no tiene lógica, solo crea el menú y lo inicia.
 * Toda la lógica del sistema de notas está en MenuNotas.
 */
public class SistemaNotas {

    public static void main(String[] args) {
        MenuNotas menu = new MenuNotas();
        menu.iniciar();
    }
}
