<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.studentmanagement.beans.Student" %>
<html>
<head><title>Students List</title></head>
<body>
<h2>List of Students</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <%
    List<Student> students = (List<Student>) request.getAttribute("students");
    for(Student student:students){
    %>
         <td><%= student.getId() %></td>
            <td><%= student.getName() %></td>
            <td><%= student.getEmail() %></td>
        </tr>
        <% } %>
        </table>
        </body>
        </html>