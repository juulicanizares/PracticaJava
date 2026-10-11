package AgendaContactosFunciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Agenda {

    private static final int LONGITUD_TELEFONO = 9; 
    private static final String ARCHIVO = "agenda.txt";

    private ArrayList<Contacto> contactos = new ArrayList<>();

    public boolean agregarContacto(Contacto nuevo) {
        if (nuevo.getNombre().isEmpty() || nuevo.getNombre().contains(";")) {
            System.out.println("Nombre inválido (no puede estar vacío ni contener ';').");
            return false;
        }
        if (!telefonoValido(nuevo.getTelefono())) {
            System.out.println("Teléfono inválido: debe tener solo números y exactamente "
                    + LONGITUD_TELEFONO + " dígitos.");
            return false;
        }
        if (!emailValido(nuevo.getEmail())) {
            System.out.println("Email inválido: debe contener '@' y '.'.");
            return false;
        }
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nuevo.getNombre()) || c.getTelefono().equals(nuevo.getTelefono())) {
                System.out.println("Ya existe un contacto con ese nombre o teléfono.");
                return false;
            }
        }
        contactos.add(nuevo);
        return true;
    }

    private boolean telefonoValido(String telefono) {
        if (telefono.length() != LONGITUD_TELEFONO) {
            return false;
        }
        for (int i = 0; i < telefono.length(); i++) {
            if (!Character.isDigit(telefono.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean emailValido(String email) {
        return email.contains("@") && email.contains(".");
    }

    public ArrayList<Contacto> buscarContacto(String texto) {
        ArrayList<Contacto> encontrados = new ArrayList<>();
        for (Contacto c : contactos) {
            if (c.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                encontrados.add(c);
            }
        }
        return encontrados;
    }

    public void mostrarTodos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }
        for (int i = 0; i < contactos.size(); i++) {
            System.out.print((i + 1) + ". ");
            contactos.get(i).mostrarInfo();
        }
    }

    public boolean eliminarContacto(String nombre) {
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                contactos.remove(i);
                return true;
            }
        }
        return false;
    }

    public void mostrarContactosPorGrupo(String grupo) {
        boolean hayAlguno = false;
        for (Contacto c : contactos) {
            if (c.getGrupo().equalsIgnoreCase(grupo)) {
                c.mostrarInfo();
                hayAlguno = true;
            }
        }
        if (!hayAlguno) {
            System.out.println("No hay contactos en el grupo \"" + grupo + "\".");
        }
    }

    public boolean guardarEnArchivo() {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(ARCHIVO))) {
            for (Contacto c : contactos) {
                escritor.println(c.aLineaDeArchivo());
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar la agenda: " + e.getMessage());
            return false;
        }
    }

    public void cargarDesdeArchivo() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return; 
        }
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) { 
                    contactos.add(new Contacto(partes[0], partes[1], partes[2], partes[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar la agenda: " + e.getMessage());
        }
    }
}
