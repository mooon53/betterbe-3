package dao;

import model.Car;

import java.util.HashMap;
import java.util.Map;

public enum CarDao {
    instance;

    private Map<String, Car> configuration = new HashMap<String, Car>();

    private CarDao(){
        Car car = new Car("1", "2020", "BMW", "3 series");
        configuration.put("0",car);
    }

    public Map<String, Car> getConfiguration() {
        return configuration;
    }
}
