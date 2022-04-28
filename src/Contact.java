import java.io.Serializable;

public class Contact implements Comparable<Contact> {
    private int id;
    private String name;
    private String phone; // Tipo String porque puede aceptar el prefijo (+34)
    private String address;
    private String email;

    public Contact(int id, String name, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int compareTo(Contact o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Id: " + id +
                ", Name: " + name +
                ", Phone: " + phone +
                ", Address: " + address +
                ", Email: " + email;
    }


    public int getId() {
        return id;
    }
}
