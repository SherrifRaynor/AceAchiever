/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Entity.Grade;
import Utilities.ConnectionManager;
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
public class ControllerGrade {

    ConnectionManager conMan = new ConnectionManager();
    Connection connect = conMan.LogOn();

    public boolean addGrade(Grade grade) {
        try {
            String query = "INSERT INTO grade (id_akun, id_subject, grade, term, note) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connect.prepareStatement(query)) {
                statement.setInt(1, grade.getId_akun());
                statement.setInt(2, grade.getId_subject());
                statement.setString(3, grade.getGrade());
                statement.setString(4, grade.getTerm());
                statement.setString(5, grade.getNote());

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }

    public List<Grade> getAllGrade(int id_akun) {
        List<Grade> grades = new ArrayList<>();

        try (PreparedStatement statement = connect.prepareStatement(
                "SELECT * FROM grade WHERE id_akun = ?")) {
            statement.setInt(1, id_akun);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Grade grade = new Grade();
                    grade.setId_grade(resultSet.getInt("id_grade"));
                    grade.setId_akun(resultSet.getInt("id_akun"));
                    grade.setId_subject(resultSet.getInt("id_subject"));
                    grade.setGrade(resultSet.getString("grade"));
                    grade.setTerm(resultSet.getString("term"));
                    grade.setNote(resultSet.getString("note"));
                    grades.add(grade);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return grades;
    }

    public boolean updateGrade(Grade grade) {

        String query = "UPDATE grade SET id_subject = ?, grade = ?, term = ?, note = ?  WHERE id_grade = ?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setInt(1, grade.getId_subject());
            statement.setString(2, grade.getGrade());
            statement.setString(3, grade.getTerm());
            statement.setString(4, grade.getNote());
            statement.setInt(5, grade.getId_grade());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteGrade(int id_grade) {

        String query = "DELETE FROM grade WHERE id_grade = ?";
        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setInt(1, id_grade);

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

    public List<Grade> getSummary(int id_akun) {
        List<Grade> grades = new ArrayList<>();

        try (PreparedStatement statement = connect.prepareStatement(
                "SELECT subject.name as subject_name, grade.term, grade.grade, grade.id_grade, grade.id_akun, grade.id_subject FROM subject JOIN grade ON subject.id_subject = grade.id_subject WHERE subject.id_akun = ?;")) {
            statement.setInt(1, id_akun);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Grade grade = new Grade();
                    grade.setId_grade(resultSet.getInt("id_grade"));
                    grade.setId_akun(resultSet.getInt("id_akun"));
                    grade.setId_subject(resultSet.getInt("id_subject"));
                    grade.setGrade(resultSet.getString("grade"));
                    grade.setTerm(resultSet.getString("term"));
                    grades.add(grade);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return grades;
    }

}
