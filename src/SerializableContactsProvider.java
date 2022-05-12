import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SerializableContactsProvider implements IContactsProvider{

    private List<Contact> readContactsList() throws ContactsException {
        List<Contact> contacts = new ArrayList<>();
        try ( ObjectInputStream input = new ObjectInputStream(new FileInputStream("resources/object.obj"))){
            while (true){
                Contact contact = (Contact) input.readObject();
                contacts.add(contact);
            }

        }catch (EOFException wd) {
        }
        catch (Exception ex) {
            throw new ContactsException("Contacts cant be loaded");
        }
        return contacts;
    }

    private void storeContactsList(List<Contact> contacts) throws ContactsException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("resources/object.obj"))){
            for (Contact c : contacts) {
                out.writeObject(c);
            }
        } catch (Exception ex) {
            throw new ContactsException("Contacts cant be saved");
        }
    }

    @Override
    public void add(Contact contact) {
        List<Contact> contacts = null;
        try {
            contacts = loadContacts();
            //        List<Contact> contacts = readContactsList();
//        contacts.add(contact);
//        saveContacts(contacts);
            if (getById(contact.getId()) == null) {
                contacts.add(contact);
                storeContactsList(contacts);
            }
        } catch (ContactsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Contact contact) {
        List<Contact> contacts = null;
        try {
            contacts = loadContacts();
            contacts.remove(contact);
            storeContactsList(contacts);
        } catch (ContactsException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void clearContacts(){
        try {
            storeContactsList(new LinkedList<>());
        } catch (ContactsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Contact contact) {
        List<Contact> contacts = null;
        try {
            contacts = loadContacts();
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getId() == contact.getId()) {
                    contacts.set(i, contact);
                    storeContactsList(contacts);
                    break;
                }
            }
        } catch (ContactsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact getById(int id) {
        try {
            for (Contact c : loadContacts()) {
                if (id == c.getId()) {
                    return c;
                }
            }
        } catch (ContactsException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Contact> loadContacts() throws ContactsException {
        return readContactsList();
    }
}
