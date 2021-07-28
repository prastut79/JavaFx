package password_manager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/DBPasswordManagerZ";
    public static final String USER = "root";
    public static final String PASSWORD = "netsos798";

    private Connection connection;
    private static DatabaseConnection dbInstance;

    /*
    Constructor
     */
    private DatabaseConnection(){
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getDbInstance() {
        if (dbInstance == null) {
            dbInstance = new DatabaseConnection();
        }
        return dbInstance;
    }

    public Connection getConnection(){
        return this.connection;
    }

}
