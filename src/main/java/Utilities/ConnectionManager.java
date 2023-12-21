
package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    
    private Connection connect;
    private String Driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/ace achiever alpha";
    private String Username = "root";
    private String Password = "";
    
    public Connection LogOn() {
        try {
            Class.forName(Driver).newInstance();
            connect = DriverManager.getConnection(url, Username, Password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connect;
    }

    public void LogOff() {
        try {
            connect.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

