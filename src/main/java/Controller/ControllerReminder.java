package Controller;

import Utilities.ConnectionManager;
import Entity.Reminder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sherr
 */
public class ControllerReminder {
    ConnectionManager conMan = new ConnectionManager();
    Connection connect = conMan.LogOn();

    // Create a new reminder
    public boolean addReminder(Reminder reminder) {
        try (PreparedStatement statement = connect.prepareStatement(
                "INSERT INTO Reminder (id_akun, title, date, note) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, reminder.getId_akun());
            statement.setString(2, reminder.getTitle());
            statement.setString(3, reminder.getDate());
            statement.setString(4, reminder.getNote());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all reminders for a specific user
    public List<Reminder> getRemindersForUser(int userId) {
        List<Reminder> reminders = new ArrayList<>();

        try (PreparedStatement statement = connect.prepareStatement(
                "SELECT * FROM reminder WHERE id_akun = ?")) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Reminder reminder = new Reminder();
                    reminder.setId_reminder(resultSet.getInt("id_reminder"));
                    reminder.setTitle(resultSet.getString("title"));

                    // Retrieve the date as a java.sql.Date and then convert it to a String
                    java.sql.Date sqlDate = resultSet.getDate("date");
                    String dateString = sqlDate.toString();

                    reminder.setDate(dateString);
                    reminder.setNote(resultSet.getString("note"));
                    reminders.add(reminder);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reminders;
    }

    // Update an existing reminder
    public boolean updateReminder(Reminder reminder) {
        try (
                PreparedStatement statement = connect.prepareStatement(
                        "UPDATE reminder SET title = ?, date = ?, note = ? WHERE id_reminder = ?")) {
            statement.setString(1, reminder.getTitle());
            statement.setString(2, reminder.getDate());
            statement.setString(3, reminder.getNote());
            statement.setInt(4, reminder.getId_reminder());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an existing reminder
    public boolean deleteReminder(int reminderId) {
        try (PreparedStatement statement = connect.prepareStatement(
                "DELETE FROM Reminder WHERE id_reminder = ?")) {
            statement.setInt(1, reminderId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
