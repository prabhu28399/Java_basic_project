package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/youtube";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // Updated to your actual MySQL password

    // JDBC driver for MySQL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String uemail = request.getParameter("uemail");
        String upwd = request.getParameter("upwd");
        String umobile = request.getParameter("umobile");

        String message;

        try {
            // Load the MySQL JDBC driver
            Class.forName(JDBC_DRIVER);

            // Establish a connection to the database
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepare SQL query to insert user data into the 'users' table
            String query = "INSERT INTO users (uname, uemail, upwd, umobile) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, uemail);
            ps.setString(3, upwd);
            ps.setString(4, umobile);

            int result = ps.executeUpdate();

            if (result > 0) {
                message = "Registration successful!";
            } else {
                message = "Registration failed. Try again.";
            }

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error occurred: " + e.getMessage();
        }

        // Pass the message to JSP
        request.setAttribute("message", message);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
