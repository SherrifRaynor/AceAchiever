/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Utilities.ConnectionManager;
import Entity.Teacher;

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
public class ControllerTeacher {

    ConnectionManager conMan = new ConnectionManager();
    Connection connect = conMan.LogOn();

    // Create a new teacher
    public boolean addTeacher(Teacher teacher) {
        // Check if the teacher already exists for the given user
        if (teacherExists(teacher.getId_akun(), teacher.getName())) {
            System.out.println("Teacher already exists for the given user.");
            return false;
        }

        String query = "INSERT INTO teacher (id_akun, name, phone, email, address, image) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, teacher.getId_akun());
            preparedStatement.setString(2, teacher.getName());
            preparedStatement.setString(3, teacher.getPhone());
            preparedStatement.setString(4, teacher.getEmail());
            preparedStatement.setString(5, teacher.getAddress());
            preparedStatement.setBytes(6, teacher.getImage());

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
    // Read all teachers

    public List<Teacher> getAllTeachers(int userId) {
        List<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement statement = connect.prepareStatement(
                "SELECT * FROM teacher WHERE id_akun = ?")) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Teacher teacher = new Teacher();
                    teacher.setId_teacher(resultSet.getInt("id_teacher"));
                    teacher.setId_akun(resultSet.getInt("id_akun"));
                    teacher.setName(resultSet.getString("name"));
                    teacher.setPhone(resultSet.getString("phone"));
                    teacher.setEmail(resultSet.getString("email"));
                    teacher.setAddress(resultSet.getString("address"));
                    teacher.setImage(resultSet.getBytes("image"));
                    teachers.add(teacher);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    // Update teacher information
    public boolean updateTeacher(Teacher teacher) {
        try (PreparedStatement statement = connect.prepareStatement(
                "UPDATE teacher SET name=?, phone=?, email=?, address=?, image=? WHERE id_teacher=?")) {
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getPhone());
            statement.setString(3, teacher.getEmail());
            statement.setString(4, teacher.getAddress());
            statement.setBytes(5, teacher.getImage());
            statement.setInt(6, teacher.getId_teacher());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a teacher
    public boolean deleteTeacher(int teacherId) {
        try (PreparedStatement statement = connect.prepareStatement(
                "DELETE FROM teacher WHERE id_teacher=?")) {
            statement.setInt(1, teacherId);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Helper method to check if a teacher already exists for the given user
    private boolean teacherExists(int id_akun, String name) {
        String query = "SELECT COUNT(*) FROM teacher WHERE id_akun = ? AND name = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id_akun);
            preparedStatement.setString(2, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);
            return count > 0;

        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return false;
        }
    }
}
