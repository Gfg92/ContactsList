import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ContactsProviderFile implements IContactsProvider {

    private final File file;
    //private final List<Contact> contacts;


    public ContactsProviderFile(File file) {
        this.file = file;
        // contacts = readContactsList();
    }

    private List<Contact> readContactsList() throws ContactsException {
        List<Contact> contacts = new ArrayList<>();
        try {
            FileReader f = new FileReader(file);
            BufferedReader reader = new BufferedReader(f);
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(";");
                Contact contact = new Contact(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], fields[4]);
                contacts.add(contact);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception ex) {
            throw new ContactsException("Contacts cant be loaded");
        }
        return contacts;
    }

    private void storeContactsList(List<Contact> contacts) throws ContactsException {
        try {
            FileWriter f = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(f);
            for (Contact c : contacts) {
                writer.write(c.getId() + ";" + c.getName() + ";" + c.getPhone() + ";" + c.getAddress() +
                        ";" + c.getEmail() + "\n");
            }
            writer.close();
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
