package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetData {
    private static Connection connection;
    private static Statement statement;
    private static final String username = "dab_di21222b_41";
    private static final String password = "hGqSYm23uiZwibLy";
    private static final String url = "jdbc:postgresql://bronto.ewi.utwente.nl/" + username + "?currentSchema=Test";

    private static void setup() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Error connecting " + e);
        }
    }

    public static List<String> getOptions(Long carId) {
        setup();
        List<String> options = new ArrayList<>();
        String query = "SELECT row_to_json(options)\n" +
                "FROM Test.options\n" +
                "WHERE car_id =" + carId;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                options.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return options;
    }

    public static List<String> getRules(Long carId) {
        setup();
        List<String> rules = new ArrayList<>();
        String query = "SELECT row_to_json(rules)\n" +
                "FROM Test.rules\n" +
                "WHERE car_id =" + carId;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                rules.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rules;

    }
    public static List<String> getCar(Long carId){
        setup();
        List<String> car = new ArrayList<>();
        String query =  "SELECT row_to_json(car)\n" +
                "FROM Test.car\n" +
                "WHERE car_id =" + carId;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                car.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }
}


//extract data of configuration from db

