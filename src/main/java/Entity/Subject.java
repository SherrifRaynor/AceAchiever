
package Entity;


public class Subject {
    private int id_subject;
    private int id_akun;
    private String name;
    private int weight;
    private String room;

    public Subject() {
    }

    public Subject(int id_akun, String name, int weight, String room) {
        this.id_akun = id_akun;
        this.name = name;
        this.weight = weight;
        this.room = room;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public int getId_akun() {
        return id_akun;
    }

    public void setId_akun(int id_akun) {
        this.id_akun = id_akun;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    

    

    

    
    
    
}
