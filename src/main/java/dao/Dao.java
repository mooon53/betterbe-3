package dao;

import model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dao {
    private static Statement statement;
    private static final String USER = "dab_di21222b_41";
    private static final String PASS = "hGqSYm23uiZwibLy";
    private static final String URL = "jdbc:postgresql://bronto.ewi.utwente.nl/" + USER + "?currentSchema=Test";

    private Dao() {
        throw new IllegalStateException("Utility class");
    }

    static {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Error connecting " + e);
        }
    }

    public static List<String> getCars() {
        List<String> cars = new ArrayList<>();
        String query = "SELECT row_to_json(car)\n" +
                "FROM Test.car\n" +
                "ORDER BY id";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                cars.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    public static String getCar(Long carId){
        String car;
        String query =  "SELECT row_to_json(car)\n" +
                "FROM Test.car\n" +
                "WHERE id =" + carId;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            car = resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    public static List<String> getOptions(Long carId) {
        List<String> options = new ArrayList<>();
        String query = "SELECT row_to_json(option)\n" +
                "FROM Test.option\n" +
                "WHERE car_id =" + carId +
                "\nORDER BY price";
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
        List<String> rules = new ArrayList<>();
        String query = "SELECT row_to_json(rule)\n" +
                "FROM Test.rule\n" +
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

    public static void addCar(Car car) {
        String query = "INSERT INTO car (id, make, model, production_year, price, drive_layout, body_type, class)\n" +
                    "VALUES(" + car.getCarId() + ", " + car.getMake() + ", " + car.getModel() + ", " + car.getProductionYear() + ", " + car.getPrice() +
                    ", " + car.getDriveLayout() + ", " + car.getBodyType() + ", " + car.getClazz() +
                    ");";
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //return true if the account exists and password matches
    public static boolean getAccount(String username, String password){
        Map<String, String> credentials = new HashMap<>();
        String query = "SELECT username, password FROM account WHERE username = "+ username +" AND password = "+password;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }
            if(rowCount == 1){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
//extract data of configuration from db

    public static void addAccount(String username, String password){
        String query = "INSERT INTO account (username,password) VALUES ("+username+", "+ password+")";
    }

    public static String getAccountInfo(String username){
        String person = "";
        String query = "SELECT row_to_json(person)\n" +
                "FROM Test.person\n" +
                "WHERE username =" + username;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            person = resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public static String getPass(String username) {
        String query = "SELECT password FROM test.account WHERE username = "+ username;
        String password = "";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password;
    }

}


//extract data of configuration from db

