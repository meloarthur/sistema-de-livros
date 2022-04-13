package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    static String URL = "jdbc:postgresql://127.0.0.1:5432/Livros";
    static String USERNAME = "postgres";
    static String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }

}
