package utils;

import java.sql.*;

public class DbConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/my_ecom";
    private static final String USERNAME = "root";
    private Connection connection;

    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, "");
            if (!this.connection.isClosed()) {
                System.out.println("Connected to database.");
            } else {
                System.out.println("Connection failed.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return this.connection;
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
