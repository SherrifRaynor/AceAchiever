
package Entity;


public class Subject {
    private int id_subject;
    private int id_akun;
    private String nama;
    private int bobot;
    private String ruangan;

    public Subject() {
    }

    public Subject(int id_akun, String nama, int bobot, String ruangan) {
    this.id_akun = id_akun;
    this.nama = nama;
    this.bobot = bobot;
    this.ruangan = ruangan;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getBobot() {
        return bobot;
    }

    public void setBobot(int bobot) {
        this.bobot = bobot;
    }

    public String getRuangan() {
        return ruangan;
    }

    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    

    
    
    
}
