package dao;

import model.Car;

import java.util.HashMap;
import java.util.Map;

public enum CarDao {
    instance;

    private Map<Long, Car> Cars = new HashMap<>();

    private CarDao(){
        Car car = new Car(0L, "2020", "BMW", "3 series");
        Cars.put(0L,car);
        car = new Car(1L, "2021", "BMW", "3 series");
        Cars.put(1L,car);
        car = new Car(2L, "2020", "Mazda", "CX7");
        Cars.put(2L,car);
    }

    public Map<Long, Car> getCars() {
        return Cars;
    }
}
