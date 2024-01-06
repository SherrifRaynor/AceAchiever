
package Entity;

/**
 *
 * @author sherr
 */
public class Reminder {
    private int id_reminder;
    private int id_akun;
    private String title;
    private String date;
    private String note;

    public Reminder() {
    }

    public Reminder(int id_akun, String title, String date, String note) {
        this.id_akun = id_akun;
        this.title = title;
        this.date = date;
        this.note = note;
    }

    public int getId_reminder() {
        return id_reminder;
    }

    public void setId_reminder(int id_reminder) {
        this.id_reminder = id_reminder;
    }

    public int getId_akun() {
        return id_akun;
    }

    public void setId_akun(int id_akun) {
        this.id_akun = id_akun;
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