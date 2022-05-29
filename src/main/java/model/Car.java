package model;

import org.json.simple.JSONObject;

public class Car {
    private Long carId;
    private String productionDate;
    private String make;
    private String model;

    public Car(){}

    public Car(Long carId, String productionDate, String make, String model){
        this.carId = carId;
        this.productionDate = productionDate;
        this.make = make;
        this.model = model;
    }

    public JSONObject getCar() {
        JSONObject response = new JSONObject();
        response.put("carId", carId);
        response.put("productionDate", productionDate);
        response.put("make", make);
        response.put("model", model);
        return response;
    }

    public Long getCarId() {
        return carId;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public String getProductionDate() {
        return productionDate;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }
}
