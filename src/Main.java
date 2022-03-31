public class Main {
    public static void main(String[] args) {
        ContactsList contactsList = new ContactsList();
        Contact contact1 = new Contact("Guille", "687453789");
        Contact contact2 = new Contact("Pepe", "678543678");

        contactsList.add(contact1);
        contactsList.add(contact2);
        contactsList.remove(contact1);

        contactsList.showContacts();


    }
}
