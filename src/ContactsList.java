import java.util.ArrayList;
import java.util.List;

public class ContactsList {
    List<Contact> contacts = new ArrayList<>();

    public void add(Contact contact){
        contacts.add(contact);
    }
    public void remove(Contact contact){
        contacts.remove(contact);
    }


}
