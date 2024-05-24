package batchmain.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DbConnector {
	 // JDBC URL, user name, and password of MySQL server
    private static final String JDBC_URL = "jdbc:sqlserver://192.168.1.250:1433;databaseName=batchmaster;encrypt=false;sslProtocol=TLSv1.2;";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "P@$$w0rd";

    // JDBC variables for opening, closing and managing connection
    private static Connection connection;

    public static Connection getConnection() {
        try {
            // Initialize the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to establish database connection: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
