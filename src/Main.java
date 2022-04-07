import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactsList contactsList = new ContactsList();

        int opcion;
        do {
            System.out.println("MENU DE OPCIONES");
            System.out.println("================");
            System.out.println("1.- Añadir contacto");
            System.out.println("2.- Borrar contacto");
            System.out.println("3.- Borrar todos los contactos");
            System.out.println("4.- Modificar contacto");
            System.out.println("5.- Mostrar contactos");
            System.out.println("6.- Salir");
            System.out.println("Introduzca su eleccion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Introduce id");
                    int id = sc.nextInt();
                    Contact newContact = createContact(id, sc);
                    contactsList.add(newContact);
                    break;
                case 2:
                    contactsList.showContacts();
                    System.out.println("Introduce el id que desea borrar");
                    id = sc.nextInt();
                    Contact contact = contactsList.getById(id);
                    if(contact == null){
                        System.out.println("El id introducido no es valido");
                    }else {
                        System.out.println("El contacto eliminado es: " + contact);
                        contactsList.remove(contact);
                    }
                    break;
                case 3:
                    System.out.println("¿Quieres eliminar todos los contactos? (S/N)");
                    char elige = sc.next().charAt(0);
                    if (elige == 'S') {
                        contactsList.clearContacts();
                        System.out.println("Has eliminado todos los contactos");
                    }
                    break;
                case 4:
                    contactsList.showContacts();
                    System.out.println("Introduce el id a modificar");
                    id = sc.nextInt();
                    Contact c = contactsList.getById(id);
                    if (c == null){
                        System.out.println("El contacto no existe");
                    }else {
                        System.out.println("El contacto a modificar es: " + c);
                        Contact updateContact = createContact(id, sc);
                        contactsList.update(updateContact);
                    }
                    break;
                case 5:
                    contactsList.showContacts();
                    break;
                case 6:
                    System.out.println("Fin de programa");
                    break;

            }
        } while (opcion != 6);

    }

    private static Contact createContact(int id, Scanner sc) {
        System.out.println("Introduzca nombre de contacto:");
        String nombre = sc.next();
        System.out.println("Introduce el telefono");
        String telefono = sc.next();
        System.out.println("Introduce la direccion");
        String direccion = sc.next();
        System.out.println("Introduce el email");
        String email = sc.next();
        return new Contact(id, nombre, telefono, direccion, email);
    }



}
