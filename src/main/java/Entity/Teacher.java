
package Entity;

/**
 *
 * @author sherr
 */
public class Teacher {
    private int id_teacher;
    private int id_akun;
    private String name;
    private String phone;
    private String email;
    private String address;
    private byte[] image;

    public Teacher() {
    }

    public Teacher(int id_akun, String name, String phone, String email, String address, byte[] image) {
        this.id_akun = id_akun;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.image = image;
    }

    public int getId_akun() {
        return id_akun;
    }

    public void setId_akun(int id_akun) {
        this.id_akun = id_akun;
    }

    

  
    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    
    
}
