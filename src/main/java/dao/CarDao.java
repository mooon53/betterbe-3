package dao;

import model.Car;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public enum CarDao {
    instance;

    private Map<Long, Car> Cars = new HashMap<>();

    private CarDao(){
        Car car = new Car(0L, new Date(2020, 0, 0), "BMW", "3 series");
        Cars.put(0L,car);
        car = new Car(1L, new Date(2021, 0, 0), "BMW", "3 series");
        Cars.put(1L,car);
        car = new Car(2L, new Date(2020, 0, 0), "Mazda", "CX7");
        Cars.put(2L,car);
    }

    public Map<Long, Car> getCars() {
        return Cars;
    }
}
