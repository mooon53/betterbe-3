package model;

import org.json.JSONObject;

public class Car {
    private Long id;
    private Long year;
    private Double price;
    private String make;
    private String model;
    private String layout;
    private String type;
    private String size;
    private boolean available;

    public Car(){}

    public Car(Long id, Long year, Double price, String make, String model) {
        this.id = id;
        this.year = year;
        this.price = price;
        this.make = make;
        this.model = model;
    }

    public Car(Long id, Long year, Double price, String make, String model, String layout, String type, String size) {
        this.id = id;
        this.year = year;
        this.price = price;
        this.make = make;
        this.model = model;
        this.layout = layout;
        this.type = type;
        this.size = size;
    }

    public JSONObject toJSON() {
        JSONObject response = new JSONObject();
        response.put("id", id);
        response.put("make", make);
        response.put("model", model);
        response.put("year", year);
        response.put("price", price);
        response.put("layout", layout);
        response.put("type", type);
        response.put("size", size);
        return response;
    }

    public Long getId() {
        return id;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public Long getYear() {
        return year;
    }
    public String getLayout() {
        return layout;
    }
    public Double getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    public String getSize() {
        return size;
    }
    public boolean getAvailable() {return available;}

    public void setSize(String size) {
        this.size = size;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setYear(Long year) {
        this.year = year;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setLayout(String layout) {
        this.layout = layout;
    }
    public void setAvailable(boolean available) {this.available = available;}
}
