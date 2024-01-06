package Controller;

import Entity.User;
import Utilities.UserSessionManager;
import Utilities.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerLogin {

    ConnectionManager conMan = new ConnectionManager();
    Connection connect = conMan.LogOn();

    public boolean registerUser(User user) {

        String query = "INSERT INTO akun (username, password, profile_picture) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBytes(3, user.getProfile_picture());

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("Error in registerUser: " + ex.getMessage());
            return false;
        }
    }

    public boolean loginUser(String username, String password) {
        String query = "SELECT id_akun FROM akun WHERE username = ? AND password = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int loggedInUserId = resultSet.getInt("id_akun");

                    UserSessionManager.setCurrentUserId(loggedInUserId);

                    return true; // Valid login
                } else {
                    return false; // Invalid login
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error in loginUser: " + ex.getMessage());
            return false;
        }
    }

    public boolean isUsernameExists(String username) {
        try {

            String query = "SELECT * FROM akun WHERE username = ?";
            try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next(); // If the result set has any rows, the username exists
            }
        } catch (SQLException ex) {
            System.out.println("Error in isUsernameExists: " + ex.getMessage());
            return false;
        }
    }

    public String getUsernameById(int userId) {
        String query = "SELECT username FROM akun WHERE id_akun = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("username");
                } else {
                    return "N/A"; // Return a default value or handle the case when the username is not found
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error in getUsernameById: " + ex.getMessage());
            return "N/A";
        }
    }

    public byte[] getPfpById(int userId) {
        String query = "SELECT profile_picture FROM akun WHERE id_akun = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBytes("profile_picture");
                } else {
                    return null; // Return a default value or handle the case when the username is not found
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error in getPfpById: " + ex.getMessage());
            return null;
        }
    }
}
