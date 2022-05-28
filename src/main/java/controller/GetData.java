package controller;

import org.json.JSONArray;
import org.json.simple.JSONObject;


import java.sql.*;

public class GetData {
    private String host = "bronto.ewi.utwente.nl";
    private String dbName;
    private String url = "jdbc:postgresql://"
            + host + ":6789/" + dbName;
    private Connection connection;
    private Statement statement;


    public GetData(String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading driver " + e);
        }
        try {
            this.connection = DriverManager.getConnection(this.url, username, password);
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Error connecting " + e);
        }
    }

    public String jsonRequest(String query){
        try {
            ResultSet rs = this.statement.executeQuery(query);
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            while(rs.next()){
                JSONObject config = new JSONObject();
                //add key-value to json
                config.put("ID", rs.getString("carId"));
                config.put("Production_Date", rs.getString("productionDate"));
                config.put("Make", rs.getString("make"));
                config.put("Model", rs.getString("model"));
                jsonArray.put(config);
            }
            jsonObject.put("Car_Data", jsonArray);
            return jsonObject.toJSONString();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}


//extract data of configuration from db

