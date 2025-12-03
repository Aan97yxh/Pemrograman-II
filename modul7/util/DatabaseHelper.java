package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseHelper {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_HOST = "localhost";
    private static final int DB_PORT = 3306;
    private static final String DB_NAME = "praktikum7";
    private static final String USER = "root";
    private static final String PASS = "";

    // additional params to avoid timezone / SSL issues
    private static final String PARAMS = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String DB_URL = String.format("jdbc:mysql://%s:%d/%s%s", DB_HOST, DB_PORT, DB_NAME, PARAMS);

    private DatabaseHelper() { }

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + DRIVER);
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
