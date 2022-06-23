package dao;

import model.*;
import utils.DatabaseConnectionChecker;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static utils.SQLUtils.*;

public class Dao {
    private static Statement statement;
    private static final String USER = "dab_di21222b_41";
    private static final String PASS = "hGqSYm23uiZwibLy";
    private static final String URL = "jdbc:postgresql://bronto.ewi.utwente.nl/" + USER + "?currentSchema=betterbe";

    private Dao() {
        throw new IllegalStateException("Utility class");
    }

    static {
        boolean connected = false;
        Connection connection = null;
        while (!connected) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASS);
                statement = connection.createStatement();
                connected = true;
            } catch (SQLException e) {
                System.err.println("Error connecting " + e);
            }
        }
        DatabaseConnectionChecker dbCheck = new DatabaseConnectionChecker(connection, URL, USER, PASS);
        Thread thread = new Thread(dbCheck, "Connection checker");
        thread.start();
    }

    public static void replaceStatement(Statement newStatement) {
        statement = newStatement;
    }

    public static List<String> getCars() {
        List<String> cars = new ArrayList<>();
        String query = "SELECT row_to_json(car)\n" +
                "FROM car\n" +
                "ORDER BY id";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                cars.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public static String getCar(Long carId){
        String car = "";
        String query =  "SELECT row_to_json(car)\n" +
                "FROM car\n" +
                "WHERE id =" + carId;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            car = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public static List<String> getOptions(Long carId) {
        List<String> options = new ArrayList<>();
        String query = "SELECT row_to_json(option)\n" +
                "FROM option\n" +
                "WHERE car_id =" + carId +
                "AND end_date IS NULL" +
                "\nORDER BY price";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                options.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options;
    }

    public static List<String> getOptions() {
        List<String> options = new ArrayList<>();
        String query = "SELECT row_to_json(option)\n" +
                "FROM option\n" +
                "\nORDER BY id";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                options.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options;
    }

    public static List<String> getRules(Long carId) {
        List<String> rules = new ArrayList<>();
        String query = "SELECT row_to_json(rule)\n" +
                "FROM rule\n" +
                "WHERE car_id =" + carId;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                rules.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    public static void addOptions(List<Option> options) {
        for (Option option : options) {
            addOption(option);
        }
    }

    public static void addRule(Rule rule) {

        String query = "INSERT INTO rule (options, exclusive, mandatory, car_id)\n" +
                "VALUES('" + intArrayToSqlArray(rule.getOptions()) + "', " + rule.getExclusive() + ", " + rule.getMandatory() + ", " + rule.getCarId() + ");";
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addRules(List<Rule> rules) {
        for (Rule rule : rules) {
            addRule(rule);
        }
    }

    //return true if the account exists and password matches
    public static boolean hasAccount(String username, String password){
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
            e.printStackTrace();
        }
        return false;
    }
//extract data of configuration from db

    public static void addAccount(String username, String password, boolean employee){
        String query = "INSERT INTO account (username, password, employee) VALUES ('"+username+"', '"+ password+"'," + employee + " )";
        System.out.println(query);
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addAccount(String username, String password) {
        addAccount(username, password, false);
    }

    public static String getUser(String username){
        String person = "";
        String query = "SELECT row_to_json(person)\n" +
                "FROM person\n" +
                "WHERE username ='" + username + "'";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            person = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public static String getAccount(String email){
        String account = "";
        String query = "SELECT row_to_json(account)\n" +
                "FROM account\n" +
                "WHERE email ='" + email + "'";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            account = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public static String getPass(String email) {
        String query = "SELECT password FROM account WHERE email = '"+ email +"'";
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

    public static List<String> getHistoricalData(Long carId){
        List<String> data = new ArrayList<>();
        //change to json output, aka row_to_JSON
//        SELECT XMLAGG(XMLFOREST(o.start_date,
//                c.make,
//                c.model,
//                c.year,
//                o.option_type,
//                o.value,
//                o.price)) as timeline
//
//        FROM car c,
//                option o
//        WHERE c.id = o.car_id
        String query = "SELECT row_to_json(option)\n" +
                "FROM option\n" +
                "WHERE car_id =" + carId +
                "\nORDER BY price";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data.add(resultSet.getString(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}


//extract data of configuration from db

