package Entity;

/**
 *
 * @author sherr
 */
public class Exam {
    private int id_exam;
    private int id_akun;
    private int id_subject;
    private String title;
    private String date;
    private String category;
    private String note;

    public Exam() {
    }

    public Exam(int id_akun, int id_subject, String title, String date, String category, String note) {
        this.id_akun = id_akun;
        this.id_subject = id_subject;
        this.title = title;
        this.date = date;
        this.category = category;
        this.note = note;
    }

    public int getId_exam() {
        return id_exam;
    }

    public void setId_exam(int id_exam) {
        this.id_exam = id_exam;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
     
    
}
