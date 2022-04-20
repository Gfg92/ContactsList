public interface IContactsList {
    void add(Contact contact);

    void remove(Contact contact);

    void showContacts();

    void clearContacts();

    boolean update(Contact contact);

    Contact getById(int id);
}
