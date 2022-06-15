package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dao {
    private static Statement statement;
    private static final String USER = "dab_di21222b_41";
    private static final String PASS = "hGqSYm23uiZwibLy";
    private static final String URL = "jdbc:postgresql://bronto.ewi.utwente.nl/" + USER + "?currentSchema=betterbe";

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
                "FROM betterbe.car\n" +
                "ORDER BY id";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                cars.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return cars;
    }

    public static String getCar(Long carId){
        String car = "";
        String query =  "SELECT row_to_json(car)\n" +
                "FROM betterbe.car\n" +
                "WHERE id =" + carId;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            car = resultSet.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return car;
    }

    public static List<String> getOptions(Long carId) {
        List<String> options = new ArrayList<>();
        String query = "SELECT row_to_json(option)\n" +
                "FROM betterbe.option\n" +
                "WHERE car_id =" + carId +
                "\nORDER BY price";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                options.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return options;
    }

    public static List<String> getOptions() {
        List<String> options = new ArrayList<>();
        String query = "SELECT row_to_json(option)\n" +
                "FROM betterbe.option\n" +
                "\nORDER BY id";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                options.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return options;
    }

    public static List<String> getRules(Long carId) {
        List<String> rules = new ArrayList<>();
        String query = "SELECT row_to_json(rule)\n" +
                "FROM betterbe.rule\n" +
                "WHERE car_id =" + carId;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                rules.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return rules;

    }

    public static void addCar(Car car) {
        String query = "INSERT INTO car (id, make, model, year, price, layout, type, size)\n" +
                    "VALUES(" + car.getId() + ",'" + car.getMake() + "', '" + car.getModel() + "', " + car.getYear() + ", " + car.getPrice() +
                    ", '" + car.getLayout() + "', '" + car.getType() + "', '" + car.getSize() +
                    "');";
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void addOption(Option option) {
        String query = "INSERT INTO option (id, value, manufacturer, car_id, option_type, price, start_date)\n" +
                "VALUES(" + option.getId() + ",'" + option.getValue() + "','"  + option.getManufacturer() + "'," +
                option.getCarID() + ",'" + option.getOptionType() + "'," + option.getPrice() + ",'" +
                option.getStartDate() + "');";
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void addOptions(List<Option> options) {
        for (Option option : options) {
            addOption(option);
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
            System.out.println(e.getStackTrace());
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
                "FROM person\n" +
                "WHERE username =" + username;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            person = resultSet.getString(1);
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
        }
        return person;
    }

    public static String getPass(String username) {
        String query = "SELECT password FROM account WHERE username = "+ username;
        String password = "";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password;
    }

}


//extract data of configuration from db

