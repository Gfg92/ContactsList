import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsProviderFile implements IContactsProvider {

    private final File file;
    private final List<Contact> contacts;

    public ContactsProviderFile(File file) {
        this.file = file;
        contacts = readContactsList();
    }

    private List<Contact> readContactsList() {
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
            ex.printStackTrace();
        }
        return contacts;
    }

    private void storeContactsList() {
        try {
            FileWriter f = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(f);
            for (Contact c : contacts) {
                writer.write(c.getId() + ";" + c.getName() + ";" + c.getPhone() + ";" + c.getAddress() +
                        ";" + c.getEmail() + "\n");
            }
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void add(Contact contact) {
//        List<Contact> contacts = readContactsList();
//        contacts.add(contact);
//        saveContacts(contacts);
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
    public void clearContacts() {
        contacts.clear();
        storeContactsList();
    }

    @Override
    public void update(Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == contact.getId()) {
                contacts.set(i, contact);
                storeContactsList();
                break;
            }
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

    @Override
    public List<Contact> loadContacts() {
        return contacts;
    }
}
