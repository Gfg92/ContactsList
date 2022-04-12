import java.util.*;

public class ContactsListHardcoded implements IContactsList {
    private final List<Contact> contacts = new ArrayList<>();

    public ContactsListHardcoded() {
        loadContacts();
    }

    private boolean isRegistered(Contact contact) {
        boolean registered = false;
        for (Contact c : contacts) {
            if (contact.getPhone().equals(c.getPhone())) {
                registered = true;
                break;
            }
        }
        return registered;
    }

    @Override
    public void add(Contact contact) {
        if (!isRegistered(contact)) {
            contacts.add(contact);
        }
    }

    @Override
    public boolean remove(Contact contact) {
        // Encontrar la posición del contacto a borrar
        int position = -1;
        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            if (c.getId() == contact.getId()) {
                position = i;
                break;
            }
        }
        // Comprobar si se ha encontrado el contacto
        if (position == -1) {
            return false;
        }
        contacts.remove(contact);
        return true;
    }

    @Override
    public void showContacts() {
        Collections.sort(contacts);
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private void loadContacts() {
        contacts.add(new Contact(1, "Paco", "678549384", "Calle Juan", "asñl@ieselcaminas.org"));
        contacts.add(new Contact(2, "Juan", "678549376", "Calle Juan", "uqwoyr@ieselcaminas.org"));
        contacts.add(new Contact(3, "Fran", "645372876", "Calle Menor", "asdfa@ieselcaminas.org"));
    }

    @Override
    public void clearContacts() {
        contacts.clear();
    }

    @Override
    public boolean update(Contact contact) {
        // Encontrar la posición del contacto a modificar
        int position = -1;
        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            if (c.getId() == contact.getId()) {
                position = i;
                break;
            }
        }
        // Comprobar si se ha encontrado el contacto
        if (position == -1) {
            return false;
        }
        contacts.set(position, contact);
        return true;
    }

    @Override
    public Contact getById(int id) {
        for (Contact c : contacts) {
            if (id == c.getId()) {
                return c;
            }
        }
        return null;
    }

}
