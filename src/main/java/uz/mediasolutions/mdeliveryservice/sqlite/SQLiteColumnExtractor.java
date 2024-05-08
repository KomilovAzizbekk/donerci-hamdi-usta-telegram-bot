package uz.mediasolutions.mdeliveryservice.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLiteColumnExtractor {
    /**
     * Establishes a connection to the SQLite database.
     */
    public static Connection connect(String dbFilePath) {
        Connection connection = null;
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Establish a connection to the SQLite database file
            String url = "jdbc:sqlite:" + dbFilePath;
            connection = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    /**
     * Retrieves a specific column from the specified table and returns it as a List.
     */
    public static List<String> getColumnData(String dbFilePath, String tableName, String columnName) {
        List<String> columnData = new ArrayList<>();
        String query = String.format("SELECT %s FROM %s", columnName, tableName);

        try (Connection conn = connect(dbFilePath);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Loop through the result set to gather the column data
            while (rs.next()) {
                columnData.add(rs.getString(columnName));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return columnData;
    }

//    public static void main(String[] args) {
//        // Provide the path to your .sqlite3 file here
//        String dbFilePath = "old_db.sqlite3";
//        String tableName = "bot_user";
//        String columnName = "user_id";
//
//        // Retrieve the column data
//        List<String> columnData = getColumnData(dbFilePath, tableName, columnName);
//
//        // Print the column data for verification
//        for (String data : columnData) {
//            System.out.println(data);
//        }
//    }
}
