package Controller;

import Utilities.ConnectionManager;
import Entity.Homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ControllerHomework {

    ConnectionManager conMan = new ConnectionManager();
    Connection connect = conMan.LogOn();

    // Create a new homework
    public boolean addHomework(Homework homework) {

        String query = "INSERT INTO homework (id_akun, id_subject, title, date, note) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, homework.getId_akun());
            preparedStatement.setInt(2, homework.getId_subject());
            preparedStatement.setString(3, homework.getTitle());
            preparedStatement.setString(4, homework.getDate());
            preparedStatement.setString(5, homework.getNote());

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    // Retrieve all homeworks for a specific user and subject
    public List<Homework> getAllHomeworks(int id_akun, int id_subject) {
        List<Homework> homeworks = new ArrayList<>();

        try (PreparedStatement statement = connect.prepareStatement(
                "SELECT * FROM homework WHERE id_akun = ? AND id_subject = ?")) {
            statement.setInt(1, id_akun);
            statement.setInt(2, id_subject);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Homework homework = new Homework();
                    homework.setId_homework(resultSet.getInt("id_homework"));
                    homework.setId_akun(resultSet.getInt("id_akun"));
                    homework.setId_subject(resultSet.getInt("id_subject"));
                    homework.setTitle(resultSet.getString("title"));

                    // Retrieve the date as a java.sql.Date and then convert it to a String
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.sql.Date sqlDate = resultSet.getDate("date");
                    String dateString = dateFormat.format(sqlDate);

                    homework.setDate(dateString);
                    homework.setNote(resultSet.getString("note"));
                    homeworks.add(homework);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return homeworks;
    }

    // Update a homework
    public boolean updateHomework(Homework homework) {
        String query = "UPDATE homework SET title = ?, date = ?, id_subject = ? , note = ? WHERE id_homework = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setString(1, homework.getTitle());
            preparedStatement.setString(2, homework.getDate());
            preparedStatement.setInt(3, homework.getId_subject());
            preparedStatement.setString(4, homework.getNote());
            preparedStatement.setInt(5, homework.getId_homework());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    // Delete a homework
    public boolean deleteHomework(int id_homework) {
        String query = "DELETE FROM homework WHERE id_homework = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_homework);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

}
