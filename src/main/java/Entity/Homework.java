
package Entity;

/**
 *
 * @author sherr
 */
public class Homework {
    private int id_homework;
    private int id_akun;
    private int id_subject;
    private String title;
    private String date;
    private String note;

    public Homework() {
    }

    public Homework(int id_akun, int id_subject, String title, String date, String note) {
        this.id_akun = id_akun;
        this.id_subject = id_subject;
        this.title = title;
        this.date = date;
        this.note = note;
    }

    public int getId_homework() {
        return id_homework;
    }

    public void setId_homework(int id_homework) {
        this.id_homework = id_homework;
    }

    public int getId_akun() {
        return id_akun;
    }

    public void setId_akun(int id_akun) {
        this.id_akun = id_akun;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
   
    
    
}
