package AgendaContactos;

public class Agenda {

    private static final int MAX_CONTACTOS = 20;

    private Contacto[] contactos = new Contacto[MAX_CONTACTOS];
    private int cantidad = 0; 

    public boolean agregarContacto(Contacto nuevo) {
        if (cantidad == MAX_CONTACTOS) {
            System.out.println("La agenda está llena.");
            return false;
        }
        for (int i = 0; i < cantidad; i++) {
            boolean mismoNombre = contactos[i].getNombre().equalsIgnoreCase(nuevo.getNombre());
            boolean mismoTelefono = contactos[i].getTelefono().equals(nuevo.getTelefono());
            if (mismoNombre || mismoTelefono) {
                System.out.println("Ya existe un contacto con ese nombre o teléfono.");
                return false;
            }
        }
        contactos[cantidad] = nuevo;
        cantidad++;
        return true;
    }

    public Contacto buscarContacto(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                return contactos[i];
            }
        }
        return null;
    }

    public void mostrarTodos() {
        if (cantidad == 0) {
            System.out.println("La agenda está vacía.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            System.out.print((i + 1) + ". ");
            contactos[i].mostrarInfo();
        }
    }

    public boolean eliminarContacto(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre)) {
                for (int j = i; j < cantidad - 1; j++) {
                    contactos[j] = contactos[j + 1];
                }
                contactos[cantidad - 1] = null;
                cantidad--;
                return true;
            }
        }
        return false;
    }
}
