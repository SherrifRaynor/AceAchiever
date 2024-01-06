package Controller;

import Utilities.ConnectionManager;
import Entity.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerSubject {

    ConnectionManager conMan = new ConnectionManager();
    Connection connect = conMan.LogOn();

    // Create a new subject
    public boolean addSubject(Subject subject) {
        // Check if the subject already exists for the given user
        if (subjectExists(subject.getId_akun(), subject.getName(), subject.getWeight(), subject.getRoom())) {
            System.out.println("Subject already exists for the given user.");
            return false;
        }

        String query = "INSERT INTO subject (id_akun, name, weight, room) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, subject.getId_akun());
            preparedStatement.setString(2, subject.getName());
            preparedStatement.setInt(3, subject.getWeight());
            preparedStatement.setString(4, subject.getRoom());

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    // Retrieve all subjects for a specific user
    public List<Subject> getAllSubjects(int id_akun) {
        List<Subject> subjects = new ArrayList<>();
        String query = "SELECT * FROM subject WHERE id_akun = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_akun);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Subject subject = new Subject();
                    subject.setId_subject(resultSet.getInt("id_subject"));
                    subject.setName(resultSet.getString("name"));
                    subject.setWeight(resultSet.getInt("weight"));
                    subject.setRoom(resultSet.getString("room"));

                    subjects.add(subject);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

// Update a subject
    public boolean updateSubject(Subject subject) {
        String query = "UPDATE subject SET name = ?, weight = ?, room = ? WHERE id_subject = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getWeight());
            preparedStatement.setString(3, subject.getRoom());
            preparedStatement.setInt(4, subject.getId_subject());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    // Delete a subject
    public boolean deleteSubject(int id_subject) {
        String query = "DELETE FROM subject WHERE id_subject = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_subject);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    // Helper method to check if a subject already exists for the given user
    private boolean subjectExists(int id_akun, String name, int weight, String room) {
        String query = "SELECT COUNT(*) FROM subject WHERE id_akun = ? AND name = ? AND weight = ? AND room = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_akun);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, weight);
            preparedStatement.setString(4, room);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);
            return count > 0;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public List<String> getAllSubjectTitles(int id_akun) {
        List<String> titles = new ArrayList<>();
        String query = "SELECT name FROM subject WHERE id_akun = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_akun);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("name");
                titles.add(title);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return titles;
    }

    public int getSubjectIdByTitle(int id_akun, String subjectTitle) {
        String query = "SELECT id_subject FROM subject WHERE id_akun = ? AND name = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_akun);
            preparedStatement.setString(2, subjectTitle);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id_subject");
            } else {
                // Handle the case where the subject with the given title is not found
                System.out.println("Subject not found for title: " + subjectTitle);
                return -1; // Or throw an exception, return a default value, etc.
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return -1; // Or throw an exception, return a default value, etc.
        }
    }

    public int getSubjectIdByName(int id_akun, String subjectName) {
        String query = "SELECT id_subject FROM subject WHERE id_akun = ? AND name = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_akun);
            preparedStatement.setString(2, subjectName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id_subject");
            } else {
                // Handle the case where the subject with the given name is not found
                System.out.println("Subject not found for name: " + subjectName);
                return -1; // Or throw an exception, return a default value, etc.
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return -1; // Or throw an exception, return a default value, etc.
        }
    }

}
