package utils;

import dao.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionChecker implements Runnable {
    private Connection connection;
    private String URL;
    private String USER;
    private String PASS;

    public DatabaseConnectionChecker(Connection connection, String url, String username, String password) {
        this.connection = connection;
        this.URL = url;
        this.USER = username;
        this.PASS = password;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(60000);
                if (connection.isClosed()) {
                    connection = DriverManager.getConnection(URL, USER, PASS);
                    Dao.replaceStatement(connection.createStatement());
                }
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
