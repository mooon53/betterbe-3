package model;

import org.json.simple.JSONObject;

public class Car {
    private Long carId;
    private Long productionYear;
    private String make;
    private String model;

    public Car(){}

    public Car(Long carId, Long productionYear, String make, String model){
        this.carId = carId;
        this.productionYear = productionYear;
        this.make = make;
        this.model = model;
    }

    public JSONObject toJSON() {
        JSONObject response = new JSONObject();
        response.put("carId", carId);
        response.put("make", make);
        response.put("model", model);
        response.put("productionYear", productionYear);
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
    public Long getproductionYear() {
        return productionYear;
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
    public void setproductionYear(Long productionYear) {
        this.productionYear = productionYear;
    }
}
