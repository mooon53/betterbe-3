package model;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static dao.Dao.*;
import static utils.JSONUtils.*;

public class Cart {

    Car car;
    ArrayList<Option> options;

    public Cart() {
    }

    public Cart(Long carId) {
        this(carId, new ArrayList<>());
    }

    public Cart(Long carId, ArrayList<Option> options) {
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