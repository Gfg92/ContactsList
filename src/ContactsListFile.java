import java.io.*;

public class ContactsListFile implements IContactsList {
    private final File file;

    public ContactsListFile(File file) {
        this.file = file;
    }

    private boolean isRegistered(Contact contact) {
        try {
            FileInputStream input = new FileInputStream(file);

            String text = "";
            int c = input.read();
            while (c != -1) {
                text = text + (char) c;
                c = input.read();
            }

            String[] lines = text.split("\n");
            for (String line : lines) {
                String[] contactFields = line.split("#");
                Contact contact1 = new Contact(Integer.parseInt(contactFields[0]), contactFields[1], contactFields[2], contactFields[3], contactFields[4]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(Contact contact) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean remove(Contact contact) {
        return false;
    }

    @Override
    public void showContacts() {

    }

    @Override
    public void clearContacts() {

    }

    @Override
    public boolean update(Contact contact) {
        return false;
    }

    @Override
    public Contact getById(int id) {
        return null;
    }
}
