package model;

import org.json.simple.JSONObject;

public class Car {
    private Long carId;
    private Long productionYear;
    private Double price;
    private String make;
    private String model;

    public Car(){}

    public Car(Long carId, Long productionYear, Double price, String make, String model){
        this.carId = carId;
        this.productionYear = productionYear;
        this.price = price;
        this.make = make;
        this.model = model;
    }

    public JSONObject toJSON() {
        JSONObject response = new JSONObject();
        response.put("carId", carId);
        response.put("make", make);
        response.put("model", model);
        response.put("productionYear", productionYear);
        response.put("price", price);
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
    public Long getProductionYear() {
        return productionYear;
    }

    public Double getPrice() {
        return price;
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
    public void setProductionYear(Long productionYear) {
        this.productionYear = productionYear;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
