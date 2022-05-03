import java.util.ArrayList;
import java.util.List;

public class ContactsProviderHardcoded implements IContactsProvider {
    private final List<Contact> contacts = new ArrayList<>();

    public ContactsProviderHardcoded() {
        loadContacts();
    }

    @Override
    public void add(Contact contact) {
        if (getById(contact.getId()) == null) {
            contacts.add(contact);
        }
    }

    @Override
    public void remove(Contact contact) {
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
            return;
        }
        contacts.remove(contact);
    }

    public List<Contact> loadContacts() {
        return contacts;
    }

    @Override
    public void clearContacts() {
        contacts.clear();
    }

    @Override
    public void update(Contact contact) {
        // Encontrar la posición del contacto a modificar
        int position = -1;
        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            if (c.getId() == contact.getId()) {
                position = i;
                break;
            }
        }
        if (position >= 0) {
            contacts.set(position, contact);
        }
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
