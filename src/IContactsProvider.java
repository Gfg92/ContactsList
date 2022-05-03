import java.util.List;

public interface IContactsProvider {
    void add(Contact contact);

    void remove(Contact contact);

    void clearContacts();

    void update(Contact contact);

    Contact getById(int id);

    List<Contact> loadContacts() throws LoadContactsException;
}
