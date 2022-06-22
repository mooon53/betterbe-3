package model;

import java.util.ArrayList;
import java.util.List;

public class CarOrder {
    private long carID;
    private List<Option> options = new ArrayList<>();

    public CarOrder() {
    }

    public CarOrder(Long carID, List<Option> options) {
        setCarID(carID);
        setOptions(options);
    }

    public long getCarID() {
        return carID;
    }

    public void setCarID(long carID) {
        this.carID = carID;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
