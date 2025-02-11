package com.studentmanagement.servlet;

import com.studentmanagement.beans.Student;
import com.studentmanagement.dao.StudentDao;
import com.studentmanagement.util.DBUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentServlet  extends HttpServlet {

    // Handling GET requests to list all students
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        try (Connection connection = DBUtil.getConnection()) {
            StudentDao dao = new StudentDao(connection);
            List<Student> students = dao.getAllStudents();
            request.setAttribute("students", students);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/listStudents.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    // Handling POST requests for adding new student
    protected void doPost(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // Validate input (simple check, can be expanded)
        if (name == null || email == null || name.isEmpty() || email.isEmpty()) {
            request.setAttribute("errorMessage", "Name and email cannot be empty.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/addStudent.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);

        try (Connection connection = DBUtil.getConnection()) {
            StudentDao dao = new StudentDao(connection);
            dao.addStudent(student);
            // Redirecting to the student list after successful addition
            response.sendRedirect("listStudent.jsp");
        } catch (SQLException e) {
            throw new ServletException("Database error while adding student", e);
        }
    }
}
