import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactsListFile implements IContactsList {

    private final File file;
    private final List<Contact> contacts;

    public ContactsListFile(File file) {
        this.file = file;
        contacts = readContactsList();
    }

    private List<Contact> readContactsList() {
        List<Contact> contacts = new ArrayList<>();
        try {
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream objectInput = new ObjectInputStream(input);
            //noinspection unchecked
            contacts = (List<Contact>) objectInput.readObject();
            objectInput.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contacts;
    }

    private void storeContactsList() {
        try {
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(output);
            objectOut.writeObject(contacts);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void add(Contact contact) {
        if (getById(contact.getId()) == null) {
            contacts.add(contact);
            storeContactsList();
        }
    }

    @Override
    public void remove(Contact contact) {
        contacts.remove(contact);
        storeContactsList();
    }

    @Override
    public void showContacts() {
        Collections.sort(contacts);
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    @Override
    public void clearContacts() {
        contacts.clear();
        storeContactsList();
    }

    @Override
    public boolean update(Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == contact.getId()) {
                contacts.set(i, contact);
                storeContactsList();
                return true;
            }
        }
        return false;
    }

    @Override
    public Contact getById(int id) {
        for (Contact c : contacts) {
            if(id == c.getId()){
                return c;
            }
        }
        return null;
    }
}
