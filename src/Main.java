import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactsList contactsList = new ContactsList();
        Contact contact1 = new Contact("Guille", "687453789");
        Contact contact2 = new Contact("Pepe", "678543678");
        int opcion;
        String nombre;
        String telefono;
        String direccion;
        String email;
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
                    System.out.println("Introduzca nombre de contacto:");
                    nombre = sc.next();
                    System.out.println("Introduce el telefono");
                    telefono = sc.next();
                    System.out.println("Introduce la direccion");
                    direccion = sc.next();
                    System.out.println("Introduce el email");
                    email = sc.next();
                    contactsList.add(new Contact(nombre, telefono, direccion, email));
                    break;
                case 2:
                    contactsList.remove(contact1);
                    break;
                case 3:
                    System.out.println("¿Quieres eliminar todos los contactos? (S/N)");
                    char elige = sc.next().charAt(0);
                    if (elige == 'S'){
                        contactsList.clearContacts();
                        System.out.println("Has eliminado todos los contactos");}
                    break;
                case 4: contactsList.showContacts();
                    System.out.println("¿Que contacto desea modificar?");
                    int valor;
                    System.out.println("Introduce un valor");
                    valor = sc.nextInt();


                    break;
                case 5:
                    contactsList.showContacts();
                    break;
                case 6:
                    System.out.println("Fin de programa");
                    break;

            }
        } while (opcion != 6);


//        contactsList.add(contact1);
//        contactsList.add(contact2);
//        contactsList.remove(contact1);
//        contact2.setName("Juan");
//
//        contactsList.showContacts();
//
//        contactsList.clearContacts();
//        contactsList.showContacts();

    }
}
