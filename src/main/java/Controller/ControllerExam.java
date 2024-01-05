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
            ex.printStackTrace();
            return false;
        }
    }

    // READ
    public List<Exam> getAllExams(int id_akun) {
        List<Exam> exams = new ArrayList<>();
        try {
            String query = "SELECT * FROM exam WHERE id_akun = ?";
            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setInt(1, id_akun);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Exam exam = new Exam();
                        exam.setId_exam(resultSet.getInt("id_exam"));
                        exam.setId_akun(resultSet.getInt("id_akun"));
                        exam.setId_subject(resultSet.getInt("id_subject"));
                        exam.setTitle(resultSet.getString("title"));
                        exam.setDate(resultSet.getString("date"));
                        exam.setCategory(resultSet.getString("category"));
                        exam.setNote(resultSet.getString("note"));

                        exams.add(exam);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exams;
    }

    // UPDATE
    public boolean updateExam(Exam exam) {
        try {
            String query = "UPDATE exam SET id_akun = ?, id_subject = ?, title = ?, date = ?, category = ?, note = ? WHERE id_exam = ?";
            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setInt(1, exam.getId_akun());
                statement.setInt(2, exam.getId_subject());
                statement.setString(3, exam.getTitle());
                statement.setString(4, exam.getDate());
                statement.setString(5, exam.getCategory());
                statement.setString(6, exam.getNote());
                statement.setInt(7, exam.getId_exam());

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteExam(int id_exam) {
        try {
            String query = "DELETE FROM exam WHERE id_exam = ?";
            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setInt(1, id_exam);

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}