/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Utilities.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import Entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sherr
 */
public class ControllerAccount {

    ConnectionManager conMan = new ConnectionManager();
    Connection connect = conMan.LogOn();

    public boolean updateUsername(User user) {
        String query = "UPDATE akun SET username = ? WHERE id_akun = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setInt(2, user.getId_user());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean updatePassword(User user) {
        String query = "UPDATE akun SET password = ? WHERE id_akun = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getId_user());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean deleteAccount(int id_akun) {
        String query = "DELETE FROM akun WHERE id_akun = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_akun);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public String getPasswordById(int userId) {
        String query = "SELECT password FROM akun WHERE id_akun = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password");
                } else {
                    return ""; // Return a default value or handle the case when the password is not found
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error in getPasswordById: " + ex.getMessage());
            return "";
        }
    }
    
    public boolean updateProfilePicture(User user) {
        String query = "UPDATE akun SET profile_picture = ? WHERE id_akun = ?";
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setBytes(1, user.getProfile_picture()); 
            preparedStatement.setInt(2, user.getId_user());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    

}
