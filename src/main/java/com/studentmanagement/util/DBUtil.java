package com.studentmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL= "jdbc:postgresql://localhost:5432/jsp_student";
    private static final String username = "postgres";
    private static final String password = "postgres";
    public static Connection getConnection() throws SQLException{
        try {
            return DriverManager.getConnection(URL, username, password);
        }catch( SQLException e){
            throw  new SQLException("Database connection error",e);
        }
    }

}
