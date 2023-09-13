package br.edu.imepac.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String user = "root";
    private static final String password = "admin";
    private static final String connectionDatabase = "jdbc:mysql://localhost/db_back";
    private static final String driveConnection = "com.mysql.cj.jdbc.Driver";

    private static Connection connection;

    public static Connection getInstance() throws SQLException, ClassNotFoundException {
        if (connection == null) {
            Class.forName(driveConnection);
            connection = DriverManager.getConnection(connectionDatabase, user, password);
        }
        return connection;
    }
}
