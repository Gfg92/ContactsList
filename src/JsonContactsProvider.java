import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JsonContactsProvider implements IContactsProvider {

    private final Gson gson = new Gson();

    private List<Contact> readContactsList() throws ContactsException {
        try {
            FileReader reader = new FileReader("resources/contacts.json");
            BufferedReader r = new BufferedReader(reader);
            //Type typeContacts = new TypeToken<List<Contact>>(){}.getType();
            //List<Contact> contacts = gson.fromJson(r, typeContacts);
            Contact[] contacts = gson.fromJson(r, Contact[].class);
            if (contacts == null) {
                contacts = new Contact[]{};
                // contacts = new ArrayList();
            }
            r.close();
            // return contacts;
            return Arrays.asList(contacts);
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (Exception ex) {
            throw new ContactsException("Contacts cant be loaded");
        }
    }

    private void storeContactsList(List<Contact> contacts) throws ContactsException {
        try {
            FileWriter writer = new FileWriter("resources/contacts.json");
            BufferedWriter w = new BufferedWriter(writer);
            gson.toJson(contacts, w);
            w.close();
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
    public void clearContacts() {
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
