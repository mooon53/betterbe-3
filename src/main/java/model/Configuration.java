package model;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static dao.Dao.*;
import static utils.JSONUtils.*;

public class Configuration {

    Car car;
    ArrayList<Option> options;

    public Configuration() {
    }

    public Configuration(Long carId) {
        this(carId, new ArrayList<>());
    }

    public Configuration(Long carId, ArrayList<Option> options) {
        this.car = getCarObj(carId);
        this.options = options;
    }

    private Car getCarObj(Long carID) {
        return jsonStringToCar(getCar(carID));
    }

    public void addOption(Long optionId) {
        Option option = jsonStringToOption(getOption(optionId));
        options.add(option);
    }

    public JSONObject toJSON() {
        JSONObject JSON = new JSONObject();
        JSON.put("car", car.toJSON());
        JSON.put("options", new JSONArray(options));
        return JSON;
    }

    public Car getCarObj() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }
}