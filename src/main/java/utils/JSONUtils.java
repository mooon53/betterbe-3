package utils;

import dao.Dao;
import model.*;
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
        return new Car(carJSON.getLong("id"), carJSON.getLong("year"), carJSON.getDouble("price"),
                carJSON.getString("make"), carJSON.getString("model"), carJSON.getString("layout"),
                carJSON.getString("type"), carJSON.getString("size"));
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
        } catch (JSONException e) {
        }
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

    public static Person jsonStringToPerson(String personString) {
        JSONObject personJSON = new JSONObject(personString);
        return jsonToPerson(personJSON);
    }

    public static Person jsonToPerson(JSONObject personJSON) {
        return new Person(personJSON.getString("username"), personJSON.getString("fname"), personJSON.getString("surname"), personJSON.getString("email"));
    }

    public static HistoricalData jsonStringToHistoricalData(String timelineStr) {
        System.out.println(timelineStr);
        JSONObject timelineJSON = new JSONObject(timelineStr);
        System.out.println(timelineJSON);
        return jsonToHistoricalData(timelineJSON);
    }

    //o.start_date, c.make, c.model, c.production_year, o.option_type, o.value, o.price
    public static HistoricalData jsonToHistoricalData(JSONObject timelineJSON) {
        try {
            return new HistoricalData(timelineJSON.getLong("id"), timelineJSON.getString("start_date"), timelineJSON.getString("end_date"), timelineJSON.getDouble("price"), timelineJSON.getString("option_type"), timelineJSON.getString("value"), timelineJSON.getDouble("price"));
        } catch (JSONException e) {
//            e.printStackTrace();
            return new HistoricalData(timelineJSON.getLong("id"), timelineJSON.getString("start_date"), timelineJSON.getDouble("price"), timelineJSON.getString("option_type"), timelineJSON.getString("value"), timelineJSON.getDouble("price"));
        }
    }

    public static List<HistoricalData> jsonStringsToHistoricalData(List<String> timelinesStrings) {
        List<HistoricalData> timeline = new ArrayList<>();
        for (String timelineString : timelinesStrings) {
            timeline.add(jsonStringToHistoricalData(timelineString));
        }
        return timeline;
    }

    public static Configuration jsonStringToConfiguration(String configurationJSONString) {
        JSONObject configurationJSON = new JSONObject(configurationJSONString);
        return jsonToConfiguration(configurationJSON);
    }

    public static Configuration jsonToConfiguration(JSONObject configurationJSON) {
        JSONArray optionsJSONArray = configurationJSON.getJSONArray("options");
        List<Long> optionIDs = new ArrayList<>();
        for (int i = 0; i < optionsJSONArray.length(); i++) {
            optionIDs.add(optionsJSONArray.getLong(i));
        }
        ArrayList<Option> options =(ArrayList<Option>) jsonStringsToOptions(Dao.getOptions(optionIDs));
        return new Configuration(configurationJSON.getLong("id"), configurationJSON.getLong("car"), options);
    }

    public static List<Configuration> jsonStringsToConfigurations(List<String> configurationStrings) {
        List<Configuration> configurations = new ArrayList<>();
        for (String configurationString : configurationStrings) {
            configurations.add(jsonStringToConfiguration(configurationString));
        }
        return configurations;
    }

    public static List<Configuration> jsonsToConfigurations(List<JSONObject> configurationJSONs) {
        List<Configuration> configurations = new ArrayList<>();
        for (JSONObject configurationJSON : configurationJSONs) {
            configurations.add(jsonToConfiguration(configurationJSON));
        }
        return configurations;
    }
}
