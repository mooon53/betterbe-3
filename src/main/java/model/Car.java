package model;

import org.json.JSONObject;

public class Car {
    private Long carId;
    private Long productionYear;
    private Double price;
    private String make;
    private String model;
    private String driveLayout;
    private String bodyType;
    private String clazz;

    public Car(){}

    public Car(Long carId, Long productionYear, Double price, String make, String model) {
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
    public String getDriveLayout() {
        return driveLayout;
    }
    public Double getPrice() {
        return price;
    }
    public String getBodyType() {
        return bodyType;
    }
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
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
    public void setDriveLayout(String driveLayout) {
        this.driveLayout = driveLayout;
    }
}
