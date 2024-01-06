/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entity.Exam;
import Utilities.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ControllerExam {
    
    ConnectionManager conMan = new ConnectionManager();
    Connection connect = conMan.LogOn();

    // CREATE
    public boolean addExam(Exam exam) {
        try {
            String query = "INSERT INTO exam (id_akun, id_subject, title, date, category, note) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setInt(1, exam.getId_akun());
                statement.setInt(2, exam.getId_subject());
                statement.setString(3, exam.getTitle());
                statement.setString(4, exam.getDate());
                statement.setString(5, exam.getCategory());
                statement.setString(6, exam.getNote());

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    // READ
    public List<Exam> getAllExams(int id_akun) {
        List<Exam> exams = new ArrayList<>();

        try (PreparedStatement statement = connect.prepareStatement(
                "SELECT * FROM exam WHERE id_akun = ?")) {
            statement.setInt(1, id_akun);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Exam exam = new Exam();
                    exam.setId_exam(resultSet.getInt("id_exam"));
                    exam.setId_akun(resultSet.getInt("id_akun"));
                    exam.setId_subject(resultSet.getInt("id_subject"));
                    exam.setTitle(resultSet.getString("title"));

                    // Retrieve the date as a java.sql.Date and then convert it to a String
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.sql.Date sqlDate = resultSet.getDate("date");
                    String dateString = dateFormat.format(sqlDate);

                    exam.setDate(dateString);
                    exam.setCategory(resultSet.getString("category"));
                    exam.setNote(resultSet.getString("note"));

                    exams.add(exam);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exams;
    }

    // UPDATE
    public boolean updateExam(Exam exam) {

        String query = "UPDATE exam SET id_subject = ?, title = ?, date = ?, category = ?, note = ? WHERE id_exam = ?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setInt(1, exam.getId_subject());
            statement.setString(2, exam.getTitle());
            statement.setString(3, exam.getDate());
            statement.setString(4, exam.getCategory());
            statement.setString(5, exam.getNote());
            statement.setInt(6, exam.getId_exam());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteExam(int id_exam) {

        String query = "DELETE FROM exam WHERE id_exam = ?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setInt(1, id_exam);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public String getSubjectNameById(int id_akun, int id_subject) {
        String query = "SELECT name FROM subject WHERE id_akun = ? AND id_subject =?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_akun);
            preparedStatement.setInt(2, id_subject);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("name");
            } else {
                // Handle the case where the subject with the given title is not found
                System.out.println("Name not found for id: " + id_subject);
                return "N/A"; // Or throw an exception, return a default value, etc.
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            return "N/A"; // Or throw an exception, return a default value, etc.
        }
    }
}