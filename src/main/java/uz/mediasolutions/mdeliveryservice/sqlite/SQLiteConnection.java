package uz.mediasolutions.mdeliveryservice.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    public static Connection connect(String dbFilePath) {
        Connection connection = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Establish a connection to the SQLite database file
            String url = "jdbc:sqlite:" + dbFilePath;
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        // Provide the path to your .sqlite3 file here
        String dbFilePath = "path/to/your/database.sqlite3";
        Connection conn = connect(dbFilePath);

        // Close the connection
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
