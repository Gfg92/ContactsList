import java.util.*;

public class ContactsList {
    private List<Contact> contacts = new ArrayList<>();

    public ContactsList() {
        loadContacts();
    }

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public void remove(Contact contact) {
        contacts.remove(contact);
    }

    public void showContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }
    }

    private void loadContacts() {
        contacts.add(new Contact("Paco", "678549384", "Calle Juan", "asñl@ieselcaminas.org"));
        contacts.add(new Contact("Juan", "678549376", "Calle Juan", "uqwoyr@ieselcaminas.org"));
        contacts.add(new Contact("Fran", "645372876", "Calle Menor", "asdfa@ieselcaminas.org"));
    }

    public void clearContacts(){
        contacts.clear();
    }


}
