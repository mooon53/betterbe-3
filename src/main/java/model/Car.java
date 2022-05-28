package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car {
    private String carId;
    private String produtionDate;
    private String make;
    private String model;

    public Car(){}

    public Car(String carId, String produtionDate, String make, String model){
        this.carId = carId;
        this.produtionDate = produtionDate;
        this.make = make;
        this.model = model;
    }
    public String getCarId() {
        return carId;
    }
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public String getProdutionDate() {
        return produtionDate;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setProdutionDate(String produtionDate) {
        this.produtionDate = produtionDate;
    }
}
