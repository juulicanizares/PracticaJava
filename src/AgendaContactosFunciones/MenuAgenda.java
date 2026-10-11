package AgendaContactosFunciones;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuAgenda {

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();
        agenda.cargarDesdeArchivo(); 

        int opcion = 0;
        while (opcion != 6) {
            System.out.println("\n===== AGENDA DE CONTACTOS =====");
            System.out.println("1. Registrar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Listar todos");
            System.out.println("4. Listar por grupo");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Guardar y salir");
            opcion = leerEntero(sc, "Elija una opción: ");

            switch (opcion) {
                case 1: {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine().trim();
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    System.out.print("Grupo (Familia, Trabajo, Amigos...): ");
                    String grupo = sc.nextLine().trim();
                    if (agenda.agregarContacto(new Contacto(nombre, telefono, email, grupo))) {
                        System.out.println("Contacto agregado.");
                    }
                    break;
                }
                case 2: {
                    System.out.print("Texto a buscar en el nombre: ");
                    ArrayList<Contacto> encontrados = agenda.buscarContacto(sc.nextLine().trim());
                    if (encontrados.isEmpty()) {
                        System.out.println("No se encontraron contactos.");
                    } else {
                        System.out.println("Se encontraron " + encontrados.size() + ":");
                        for (Contacto c : encontrados) {
                            c.mostrarInfo();
                        }
                    }
                    break;
                }
                case 3:
                    agenda.mostrarTodos();
                    break;
                case 4:
                    System.out.print("Grupo: ");
                    agenda.mostrarContactosPorGrupo(sc.nextLine().trim());
                    break;
                case 5:
                    System.out.print("Nombre exacto a eliminar: ");
                    if (agenda.eliminarContacto(sc.nextLine().trim())) {
                        System.out.println("Contacto eliminado.");
                    } else {
                        System.out.println("No se encontró el contacto.");
                    }
                    break;
                case 6:
                    if (agenda.guardarEnArchivo()) {
                        System.out.println("Agenda guardada. ¡Hasta pronto!");
                    }
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero.");
            }
        }
    }
}
