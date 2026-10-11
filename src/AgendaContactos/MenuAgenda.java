package AgendaContactos;

import java.util.Scanner;

public class MenuAgenda {

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();

        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n===== AGENDA DE CONTACTOS =====");
            System.out.println("1. Registrar contacto");
            System.out.println("2. Buscar contacto");
            System.out.println("3. Listar contactos");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Salir");
            opcion = leerEntero(sc, "Elija una opción: ");

            switch (opcion) {
                case 1: {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();
                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine().trim();
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    if (nombre.isEmpty() || telefono.isEmpty()) {
                        System.out.println("El nombre y el teléfono son obligatorios.");
                    } else if (agenda.agregarContacto(new Contacto(nombre, telefono, email))) {
                        System.out.println("Contacto agregado.");
                    }
                    break;
                }
                case 2: {
                    System.out.print("Nombre a buscar: ");
                    Contacto c = agenda.buscarContacto(sc.nextLine().trim());
                    if (c == null) {
                        System.out.println("No se encontró el contacto.");
                    } else {
                        c.mostrarInfo();
                    }
                    break;
                }
                case 3:
                    agenda.mostrarTodos();
                    break;
                case 4:
                    System.out.print("Nombre a eliminar: ");
                    if (agenda.eliminarContacto(sc.nextLine().trim())) {
                        System.out.println("Contacto eliminado.");
                    } else {
                        System.out.println("No se encontró el contacto.");
                    }
                    break;
                case 5:
                    System.out.println("¡Hasta pronto!");
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
