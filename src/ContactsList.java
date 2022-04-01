import java.util.*;

public class ContactsList {
    private final List<Contact> contacts = new ArrayList<>();

    public ContactsList() {
        loadContacts();
    }
    private boolean isRegistered(Contact contact){
        boolean registered = false;
        for (Contact c : contacts) {
            if (contact.getPhone().equals(c.getPhone())) {
                registered = true;
                break;
            }
        }
        return registered;
    }

    public void add(Contact contact) {
        if (!isRegistered(contact)) {
            contacts.add(contact);
        }
    }

    public void remove(Contact contact) {
        contacts.remove(contact);
    }

    public void showContacts() {
        Collections.sort(contacts);
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private void loadContacts() {
        contacts.add(new Contact("Paco", "678549384", "Calle Juan", "asñl@ieselcaminas.org"));
        contacts.add(new Contact("Juan", "678549376", "Calle Juan", "uqwoyr@ieselcaminas.org"));
        contacts.add(new Contact("Fran", "645372876", "Calle Menor", "asdfa@ieselcaminas.org"));
    }

    public void clearContacts() {
        contacts.clear();
    }


}
