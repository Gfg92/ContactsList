import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactList {
    private final IContactsProvider provider;
    private List<Contact> contacts = new ArrayList<>();


    public ContactList(IContactsProvider provider) {
        this.provider = provider;
        synchronizeFromProvider();
    }


    public void add(Contact contact) {
        provider.add(contact);
        synchronizeFromProvider();
    }

    public void remove(Contact contact) {
        provider.remove(contact);
        synchronizeFromProvider();
    }


    public void showContacts() {
        Collections.sort(contacts);
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }


    public void clearContacts() {
        provider.clearContacts();
        synchronizeFromProvider();
    }


    public void update(Contact contact) {
        provider.update(contact);
        synchronizeFromProvider();
    }


    public Contact getById(int id) {
        return provider.getById(id);
    }

    public void synchronizeFromProvider() {
        try {
            contacts = provider.loadContacts();
        }catch (ContactsException lce){
            System.err.println(lce.getMessage());
        }
    }
}
