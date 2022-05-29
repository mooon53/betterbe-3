package model;

import java.sql.Date;

public class Option {
    Long id;
    String value;
    String manufacturer;
    Long carID;
    String optionType;
    Double price;
    Date startDate;
    Date endDate;

    public Option() {}

    public Option(Long id, String value, String manufacturer, Long carID, String optionType, Double price, Date startDate) {
        new Option(id, value, manufacturer, carID, optionType, price, startDate, null);
    }

    public Option(Long id, String value, String manufacturer, Long carID, String optionType, Double price, Date startDate, Date endDate) {
        this.id = id;
        this.value = value;
        this.manufacturer = manufacturer;
        this.carID = carID;
        this.optionType = optionType;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Long getCarID() {
        return carID;
    }

    public String getOptionType() {
        return optionType;
    }

    public Double getPrice() {
        return price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
