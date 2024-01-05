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
        if (subjectExists(subject.getId_akun(), subject.getNama(), subject.getBobot(), subject.getRuangan())) {
            System.out.println("Subject already exists for the given user.");
            return false;
        }

        String query = "INSERT INTO subject (id_akun, nama, bobot, ruangan) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, subject.getId_akun());
            preparedStatement.setString(2, subject.getNama());
            preparedStatement.setInt(3, subject.getBobot());
            preparedStatement.setString(4, subject.getRuangan());

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

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id_subject = resultSet.getInt("id_subject");
                String nama = resultSet.getString("nama");
                int bobot = resultSet.getInt("bobot");
                String ruangan = resultSet.getString("ruangan");

                Subject subject = new Subject(id_akun, nama, bobot, ruangan);
                subjects.add(subject);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return subjects;
    }

    // Update a subject
    public boolean updateSubject(Subject subject) {
        String query = "UPDATE subject SET nama = ?, bobot = ?, ruangan = ? WHERE id_subject = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setString(1, subject.getNama());
            preparedStatement.setInt(2, subject.getBobot());
            preparedStatement.setString(3, subject.getRuangan());
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
    private boolean subjectExists(int id_akun, String nama, int bobot, String ruangan) {
        String query = "SELECT COUNT(*) FROM subject WHERE id_akun = ? AND nama = ? AND bobot = ? AND ruangan = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_akun);
            preparedStatement.setString(2, nama);
            preparedStatement.setInt(3, bobot);
            preparedStatement.setString(4, ruangan);

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
        String query = "SELECT nama FROM subject WHERE id_akun = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_akun);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("nama");
                titles.add(title);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return titles;
    }

    public int getSubjectIdByTitle(int id_akun, String subjectTitle) {
        String query = "SELECT id_subject FROM subject WHERE id_akun = ? AND nama = ?";

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

}
