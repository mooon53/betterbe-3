package controller;import java.sql.*;import java.util.ArrayList;import java.util.List;import model.*;// Class to access the databasepublic class Database {    private static final String username = "dab_di21222b_41";    private static final String password = "hGqSYm23uiZwibLy";    private static final String url ="jdbc:postgresql://bronto.ewi.utwente.nl/"+username+"?currentSchema=Test";    public static List<Option> getOptions(Long carId) { // Gets all options for a certain car        List<Option> options = new ArrayList<>();        /*options.add(new Option(0L, "Alpinweiss II", "BMW", 1L, "paint", 0.0, new Date(2015, 12, 15), null));        options.add(new Option(1L, "Dravitgrau metallic", "BMW", 1L, "paint", 46.25, new Date(2015, 12, 15), null));        options.add(new Option(2L, "Leder Vernasca Mokka", "BMW", 1L, "interior", 50.0, new Date(2015, 12, 15), null));        options.add(new Option(3L, "Stof Hevelius Anthrazit/Schwarz", "BMW", 1L, "interior", 0.0, new Date(2015, 12, 15), null));        options.add(new Option(4L, "M sport seats front", "BMW", 1L, "interior", 23.0, new Date(2015, 12, 15), null));*///        Long id, String value, String manufacturer, Long carID, String optionType, Double price, Date startDate        try (Connection connection = DriverManager.getConnection(url, username, password)) { //TODO: fix driver issue            String query = "SELECT * FROM dab_di21222b_41.test.options WHERE car_id = "+carId+" ORDER BY id";            Statement statement = connection.createStatement();            ResultSet results = statement.executeQuery(query);            while (results.next()) { //Create new Option object and store it for every row                options.add(new Option(results.getLong("id"), results.getString("value"),                        results.getString("manufacturer"), results.getLong("car_id"),                        results.getString("option_type"), results.getDouble("price"),                        results.getDate("start_date"), results.getDate("end_date")));            }        } catch (SQLException e){            e.printStackTrace();        }        return options;    }    public static List<Rule> getRules(Long carId) { // Gets rules for a certain car        List<Rule> rules = new ArrayList<>();//Create empty list for the rules        /*rules.add(new Rule(new Long[]{0L, 1L}, true, true, 1L));        rules.add(new Rule(new Long[]{2L, 4L}, true, false, 1L));        rules.add(new Rule(new Long[]{2L, 3L}, true, true, 1L));*/        try (Connection connection = DriverManager.getConnection(url, username, password)) { //Create a connection            String query = "SELECT * FROM dab_di21222b_41.test.rules WHERE car_id = " + carId + " ORDER BY id";            Statement statement = connection.createStatement();            ResultSet results = statement.executeQuery(query);            while (results.next()) { //Create new Option object and store it for every row                rules.add(new Rule((Long[]) results.getArray("options").getArray(), results.getBoolean("exclusive"),                        results.getBoolean("mandatory"), results.getLong("car_id")));            }        } catch (SQLException e) {            e.printStackTrace();        }        return rules;    }}