package ej3_expandido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TiendaOnline {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ---------- Inventario con productos de distintos tipos ----------
        Inventario inventario = new Inventario();
        inventario.agregarProducto(new ProductoElectronico("Laptop", "Electrónica", 2500.00, 5, 24));
        inventario.agregarProducto(new ProductoElectronico("Mouse", "Electrónica", 80.00, 20, 12));
        inventario.agregarProducto(new ProductoAlimenticio("Leche", "Alimentos", 15.00, 30, LocalDate.of(2026, 12, 31)));
        inventario.agregarProducto(new ProductoAlimenticio("Arroz", "Alimentos", 20.00, 40, LocalDate.of(2027, 6, 30)));
        inventario.agregarProducto(new Producto("Lámpara", "Hogar", 60.00, 10));

        // ---------- Clientes registrados y "login" por email ----------
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Ana", "ana@correo.com"));
        Cliente cliente = iniciarSesion(sc, clientes);
        Carrito carrito = new Carrito(cliente, inventario);

        // Lista que se está mostrando (todo el catálogo o filtrado por categoría)
        ArrayList<Producto> listaActual = inventario.getProductos();

        boolean comprando = true;
        while (comprando) {
            System.out.println("\n===== TIENDA ONLINE - Cliente: " + cliente.getNombre() + " =====");
            System.out.println("1. Ver catálogo");
            System.out.println("2. Filtrar por categoría");
            System.out.println("3. Ver todo el catálogo");
            System.out.println("4. Agregar producto al carrito");
            System.out.println("5. Finalizar compra");
            int opcion = leerEntero(sc, "Elija una opción: ");

            switch (opcion) {
                case 1:
                    mostrarLista(listaActual);
                    break;
                case 2:
                    listaActual = filtrar(sc, inventario);
                    mostrarLista(listaActual);
                    break;
                case 3:
                    listaActual = inventario.getProductos();
                    mostrarLista(listaActual);
                    break;
                case 4:
                    agregarAlCarrito(sc, carrito, listaActual);
                    break;
                case 5:
                    if (carrito.estaVacio()) {
                        System.out.println("No compró nada. ¡Vuelva pronto!");
                    } else {
                        carrito.mostrarFactura();
                    }
                    comprando = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }

    // Si el email no existe, se registra un cliente nuevo
    private static Cliente iniciarSesion(Scanner sc, ArrayList<Cliente> clientes) {
        System.out.print("Ingrese su email para comenzar: ");
        String email = sc.nextLine().trim();
        for (Cliente c : clientes) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                System.out.println("¡Bienvenido/a de nuevo, " + c.getNombre() + "!");
                return c;
            }
        }
        System.out.print("Email nuevo. Escriba su nombre para registrarse: ");
        Cliente nuevo = new Cliente(sc.nextLine().trim(), email);
        clientes.add(nuevo);
        return nuevo;
    }

    private static ArrayList<Producto> filtrar(Scanner sc, Inventario inventario) {
        ArrayList<String> categorias = inventario.getCategorias();
        System.out.println("Categorías disponibles:");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i));
        }
        int numero = leerEntero(sc, "Elija una categoría: ");
        if (numero < 1 || numero > categorias.size()) {
            System.out.println("Categoría inexistente. Se muestra todo el catálogo.");
            return inventario.getProductos();
        }
        return inventario.filtrarPorCategoria(categorias.get(numero - 1));
    }

    private static void agregarAlCarrito(Scanner sc, Carrito carrito, ArrayList<Producto> lista) {
        mostrarLista(lista);
        int numero = leerEntero(sc, "Número del producto: ");
        if (numero < 1 || numero > lista.size()) {
            System.out.println("Producto inexistente.");
            return;
        }
        int cantidad = leerEntero(sc, "Cantidad: ");
        Producto elegido = lista.get(numero - 1);
        if (carrito.agregarProducto(elegido, cantidad)) {
            System.out.println("Agregado al carrito: " + cantidad + " x " + elegido.getNombre());
        } else {
            System.out.println("No hay stock suficiente (o la cantidad no es válida).");
        }
    }

    private static void mostrarLista(ArrayList<Producto> lista) {
        System.out.println("\n--- Catálogo ---");
        for (int i = 0; i < lista.size(); i++) {
            Producto p = lista.get(i);
            String extra = p.getDetalleExtra().isEmpty() ? "" : " | " + p.getDetalleExtra();
            System.out.printf("%d. %-10s $%8.2f | stock: %-3d | %s%s%n",
                    i + 1, p.getNombre(), p.getPrecio(), p.getStock(), p.getCategoria(), extra);
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
