package model;

import org.json.simple.JSONObject;

import java.sql.Date;

public class Option {
    Long id;
    String value;
    String manufacturer;
    Long carID;
    String optionType;
    Double price;
    String startDate;
    String endDate;

    public Option() {
    }

    public Option(Long id, String value, String manufacturer, Long carID,
                String optionType, Double price, String startDate) {
        this(id, value, manufacturer, carID, optionType, price, startDate, null);
    }

    public Option(Long id, String value, String manufacturer, Long carID,
                String optionType, Double price, String startDate, String endDate) {
        this.id = id;
        this.value = value;
        this.manufacturer = manufacturer;
        this.carID = carID;
        this.optionType = optionType;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String toJSON() {
        StringBuilder optionJSON = new StringBuilder();
        optionJSON.append("{\"id\":").append(id).append(",");
        optionJSON.append("\"value\":\"").append(value).append("\",");
        optionJSON.append("\"manufacturer\":\"").append(manufacturer).append("\",");
        optionJSON.append("\"carID\":").append(carID).append(",");
        optionJSON.append("\"optionType\":\"").append(optionType).append("\",");
        optionJSON.append("\"price\":").append(price).append(",");
        optionJSON.append("\"startDate\":").append(startDate).append(",");
        optionJSON.append("\"endDate\":").append(endDate).append("}");
        return optionJSON.toString();
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

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", value='" + value + "'" +
                ", manufacturer='" + manufacturer + "'" +
                ", carID=" + carID +
                ", optionType='" + optionType + "'" +
                ", price=" + price +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
