package utils;

import model.Car;
import model.Option;
import model.Rule;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class JSONUtils {
    public static Car jsonStringToCar(String carString) {
        JSONObject carJSON = new JSONObject(carString);
        return jsonToCar(carJSON);
    }

    public static Car jsonToCar(JSONObject carJSON) {
        return new Car(carJSON.getLong("id"), carJSON.getLong("production_year"), carJSON.getDouble("price"),
                carJSON.getString("make"), carJSON.getString("model"));
    }

    public static List<Car> jsonStringsToCars(List<String> carsStrings) {
        List<Car> cars = new ArrayList<>();
        for (String carString : carsStrings) {
            cars.add(jsonStringToCar(carString));
        }
        return cars;
    }

    public static List<Car> jsonsToCars(List<JSONObject> carJSONs) {
        List<Car> cars = new ArrayList<>();
        for (JSONObject carJSON : carJSONs) {
            cars.add(jsonToCar(carJSON));
        }
        return cars;
    }

    public static Option jsonStringToOption(String optionString) {
        JSONObject optionJSON = new JSONObject(optionString);
        return jsonToOption(optionJSON);
    }

    public static Option jsonToOption(JSONObject optionJSON) {
        Option option = new Option(optionJSON.getLong("id"), optionJSON.getString("value"),
                optionJSON.getString("manufacturer"), optionJSON.getLong("car_id"),
                optionJSON.getString("option_type"), optionJSON.getDouble("price"),
                optionJSON.getString("start_date"));
        try {
            option.setEndDate(optionJSON.getString("end_date"));
        } catch (JSONException e) {}
        return option;
    }

    public static List<Option> jsonStringsToOptions(List<String> optionStrings) {
        List<Option> options = new ArrayList<>();
        for (String optionString : optionStrings) {
            options.add(jsonStringToOption(optionString));
        }
        return options;
    }

    public static List<Option> jsonsToOptions(List<JSONObject> optionJsons) {
        List<Option> options = new ArrayList<>();
        for (JSONObject optionJSON : optionJsons) {
            options.add(jsonToOption(optionJSON));
        }
        return options;
    }

    public static Rule jsonStringToRule(String ruleString) {
        JSONObject ruleJSON = new JSONObject(ruleString);
        return jsonToRule(ruleJSON);
    }

    public static Rule jsonToRule(JSONObject ruleJSON) {
        JSONArray optionsJSON = ruleJSON.getJSONArray("options");
        Long[] options = jsonArrayToLongArray(optionsJSON);
        return new Rule(options, ruleJSON.getBoolean("exclusive"),
                ruleJSON.getBoolean("mandatory"), ruleJSON.getLong("car_id"));
    }

    public static List<Rule> jsonStringsToRules(List<String> ruleStrings) {
        List<Rule> rules = new ArrayList<>();
        for (String ruleString : ruleStrings) {
            rules.add(jsonStringToRule(ruleString));
        }
        return rules;
    }

    public static List<Rule> jsonsToRules(List<JSONObject> ruleJsons) {
        List<Rule> rules = new ArrayList<>();
        for (JSONObject ruleJSON : ruleJsons) {
            rules.add(jsonToRule(ruleJSON));
        }
        return rules;
    }

    public static Long[] jsonArrayToLongArray(JSONArray array) {
        Long[] result = new Long[array.length()];
        for (int i = 0; i < array.length(); i++) {
            result[i] = array.getLong(i);
        }
        return result;
    }
}
