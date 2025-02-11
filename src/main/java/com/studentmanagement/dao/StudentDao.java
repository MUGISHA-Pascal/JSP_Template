package com.studentmanagement.dao;

import com.studentmanagement.beans.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private Connection connection;
    public StudentDao(Connection connection){
        this.connection = connection;
    }
    public void addStudent(Student student) throws SQLException{
        String query="INSERT INTO students (name ,email) VALUES (?,?)";
        try(PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1,student.getName());
            stmt.setString(2,student.getEmail());
            stmt.executeUpdate();
        }
    }
    public List<Student> getAllStudents() throws SQLException{
        List<Student> students = new ArrayList<>();
        String query =  "SELECT * FROM students";
        try(Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)){
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                students.add(student);
            }
        }
        return students;
    }
}
