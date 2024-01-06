
package Entity;


public class User {
    private int id_user;
    private String username;
    private String password;
    private byte[] profile_picture;

    public User() {
    }

    public User(String username, String password, byte[] profile_picture) {
        
        this.username = username;
        this.password = password;
        this.profile_picture = profile_picture;
    }

    

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(byte[] profile_picture) {
        this.profile_picture = profile_picture;
    }
    
    
   
    
    
    
}
