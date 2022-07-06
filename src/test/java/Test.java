import dao.Dao;
import model.Car;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Enumeration;

import static utils.SQLUtils.longArrayToSqlArray;

public class Test {
    public static void main(String[] args) {
        JSONObject carJSON = new JSONObject("{\"id\":0,\"make\":\"BMW\",\"model\":\"3 series\",\"year\":2020,\"price\":500.00,\"layout\":\"RWD\",\"type\":\"COUPE\",\"size\":\"MID_SIZE\",\"start_date\":\"2022-07-01\",\"end_date\":null}");
//        JSONObject carJSON = new JSONObject("{\"id\":5,\"make\":\"Mazda\",\"model\":\"MX-5\",\"year\":2022,\"price\":1000.0,\"layout\":\"FWD\",\"type\":\"coupe\",\"size\":\"SUBCOMPACT\",\"start_date\":\"2022-07-01\",\"end_date\":\"2022-07-01\"}");
        Car car = new Car(carJSON.getLong("id"), carJSON.getLong("year"), carJSON.getDouble("price"),
                carJSON.getString("make"), carJSON.getString("model"), carJSON.getString("layout"),
                carJSON.getString("type"), carJSON.getString("size"));
        try {
            System.out.println(carJSON.get("end_date").getClass().getName());
            System.out.println(carJSON.get("end_date").getClass().getName().getClass());
            car.setAvailable(carJSON.get("end_date").getClass().getName().equals("org.json.JSONObject$Null"));
        } catch (JSONException e) {
            System.out.println("kut");
        }
        System.out.println(carJSON.get("end_date"));
        System.out.println(carJSON.get("end_date").getClass());
        System.out.println(car.getAvailable());
        /*String[] strings = new String[]{"test", "test2","application/json"};
        ArrayList<String> headerValueStrings = new ArrayList<>();
        for (String string : strings) {
            headerValueStrings.add(string);
        }
        String[] header = headerValueStrings.toArray(new String[0]);
        String[] header2 = headerValueStrings.toArray(new String[headerValueStrings.size()]);
        for (int i = 0; i < header.length; i++) {
            System.out.println("new String[0]: " + header[i]);
            System.out.println("new String[headerValueStrings.size()]: " + header2[i]);
        }
*/
        /*Long[] originalList = new Long[]{0L, 1L, 4L};
        ArrayList<Long> longs = new ArrayList<>(List.of(originalList));
        StringBuilder result = new StringBuilder("{");
        int i = 0;
        while (i < longs.size() - 1) {
            result.append(longs.get(i)).append(", ");
            i++;
        }
        result.append(longs.get(i));
        result.append("}");
        System.out.println(result.toString());*/
        /*Long[] ruleId = new Long[]{5L, 6L, 7L};
        System.out.println(longArrayToSqlArray(ruleId));
        Dao.removeRule(ruleId);*/
    }
}
