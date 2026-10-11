package ej3_base;

import java.util.Scanner;

public class TiendaOnline {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Catálogo de productos
        Producto[] catalogo = {
            new Producto("Laptop", 2500.00, 5),
            new Producto("Mouse", 80.00, 20),
            new Producto("Teclado", 120.00, 10),
            new Producto("Audífonos", 150.00, 8)
        };
        Carrito carrito = new Carrito();

        int opcion = 0;
        while (opcion != 3) {
            System.out.println("\n===== TIENDA ONLINE =====");
            System.out.println("1. Ver catálogo");
            System.out.println("2. Agregar producto al carrito");
            System.out.println("3. Finalizar compra");
            opcion = leerEntero(sc, "Elija una opción: ");

            switch (opcion) {
                case 1:
                    mostrarCatalogo(catalogo);
                    break;
                case 2: {
                    mostrarCatalogo(catalogo);
                    int numero = leerEntero(sc, "Número del producto: ");
                    if (numero < 1 || numero > catalogo.length) {
                        System.out.println("Producto inexistente.");
                        break;
                    }
                    int cantidad = leerEntero(sc, "Cantidad: ");
                    Producto elegido = catalogo[numero - 1];
                    if (carrito.agregarProducto(elegido, cantidad)) {
                        System.out.println("Agregado al carrito: " + cantidad + " x " + elegido.getNombre());
                    } else {
                        System.out.println("No hay stock suficiente (o la cantidad no es válida).");
                    }
                    break;
                }
                case 3:
                    if (carrito.estaVacio()) {
                        System.out.println("No compró nada. ¡Vuelva pronto!");
                    } else {
                        carrito.mostrarFactura();
                    }
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }

    private static void mostrarCatalogo(Producto[] catalogo) {
        System.out.println("\n--- Catálogo ---");
        for (int i = 0; i < catalogo.length; i++) {
            System.out.printf("%d. %-12s $%8.2f  (stock: %d)%n",
                    i + 1, catalogo[i].getNombre(), catalogo[i].getPrecio(), catalogo[i].getStock());
        }
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
