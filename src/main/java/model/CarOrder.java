package model;

import java.util.ArrayList;
import java.util.List;

public class CarOrder {
    private long carID;
    private List<Integer> options = new ArrayList<>();

    public CarOrder() {
    }

    public CarOrder(Long carID) {
        this.carID = carID;
    }
    public CarOrder(Long carID, List<Integer> options) {
        this.carID = carID;
        this.options = options;
    }

    public long getCarID() {
        return carID;
    }

    public void setCarID(long carID) {
        this.carID = carID;
    }

    public List<Integer> getOptions() {
        return options;
    }

    public void setOptions(List<Integer> options) {
        this.options = options;
    }

    public void addOption(int optionId) {
        options.add(optionId);
    }

}
