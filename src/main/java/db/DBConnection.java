package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for establishing a connection to the Oracle database.
 * Provides a static method to obtain a database connection using predefined credentials.
 */
public class DBConnection {
    /** The JDBC URL for connecting to the Oracle database. */
    private static final String URL = "jdbc:oracle:thin:@calvin.humber.ca:1521:grok";

    /** The username for authenticating with the database. */
    private static final String USER = "coretech";

    /** The password for authenticating with the database. */
    private static final String PASSWORD = "Ite5220";

    /**
     * Establishes and returns a connection to the Oracle database using the predefined URL, username, and password.
     *
     * @return a Connection object to the database
     * @throws SQLException if a database access error occurs or the connection cannot be established
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

//    public static Connection getConnection() {
//        System.out.println("DB connection not set up yet.");
//        return null;
//    }
}

