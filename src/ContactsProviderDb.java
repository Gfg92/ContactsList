import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsProviderDb implements IContactsProvider {

    private Connection con = null;

    public ContactsProviderDb() {

        Statement st = null;
        String sentSQL = null;

        try {
            Class.forName("org.sqlite.JDBC");

            String url = "jdbc:sqlite:ContactsList.sqlite";
            con = DriverManager.getConnection(url);

            st = con.createStatement();

            sentSQL = "CREATE TABLE IF NOT EXISTS CONTACT(" +
                    "id INTEGER CONSTRAINT cp_id PRIMARY KEY, " +
                    "name TEXT, " +
                    "phone TEXT, " +
                    "address TEXT, " +
                    "email TEXT " +
                    ")";

            st.executeUpdate(sentSQL);
            st.close();

        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("No s'ha trobat el controlador JDBC (" + ex.getMessage() + ")");
        } finally {
            try {
                if (st != null && !st.isClosed()) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("No s'ha pogut tancar el Statement per alguna raó");
            }
//            try {
//                if (con != null && !con.isClosed()) {
//                    con.close();
//                }
//            } catch (SQLException ex) {
//                System.out.println("No s'ha pogut tancar el Connection per alguna raó");
//            }
        }
    }

    @Override
    public void add(Contact contact) {
        Statement st = null;
        String insert = "INSERT INTO CONTACT (id, name, phone, address, email) " +
                "VALUES (" + contact.getId() + ", '" + contact.getName() + "', '" + contact.getPhone() + "', '" +
                contact.getAddress() + "', '" + contact.getEmail() + "')";
        try {
            st = con.createStatement();
            st.executeUpdate(insert);
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            System.out.println("No se ha podido añadir el contacto");
        } finally {
            try {
                if (st != null && !st.isClosed()) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("No s'ha pogut tancar el Statement per alguna raó");
            }
        }
    }

    @Override
    public void remove(Contact contact) {
        Statement st = null;
        String delete = "DELETE FROM CONTACT WHERE id = " + contact.getId();
        try {
            st = con.createStatement();
            st.executeUpdate(delete);
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            System.out.println("No se ha podido eliminar el contacto");
        } finally {
            try {
                if (st != null && !st.isClosed()) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("No s'ha pogut tancar el Statement per alguna raó");
            }
        }
    }


    @Override
    public void clearContacts() {

    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public Contact getById(int id) {
        return null;
    }

    @Override
    public List<Contact> loadContacts() {
        Statement st = null;
        String select = "SELECT * FROM CONTACT";
        List<Contact> contacts = new ArrayList<>();
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                Contact c = new Contact(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("email")
                );
                contacts.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getMessage());
            System.out.println("No se ha podido mostrar los contactos");
        } finally {
            try {
                if (st != null && !st.isClosed()) {
                    st.close();
                }
            } catch (SQLException ex) {
                System.out.println("No s'ha pogut tancar el Statement per alguna raó");
            }
        }
        return contacts;
    }
}
