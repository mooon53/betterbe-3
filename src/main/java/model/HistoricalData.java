package model;

public class HistoricalData {
    //o.start_date, c.make, c.model, c.production_year, o.option_type, o.value, o.price
    private String date;
    private Double basePrice;
    private String optionType;
    private String optionValue;
    private Double optionPrice;

    public HistoricalData() {
    }

    public HistoricalData(String start_date, String optionType, String optionValue, Double optionPrice) {
        //this.basePrice = basePrice;
        this.date = start_date;
        this.optionType = optionType;
        this.optionValue = optionValue;
        this.optionPrice = optionPrice;
    }



    public Double getBasePrice() {
        return basePrice;
    }

    public Double getOptionPrice() {
        return optionPrice;
    }



    public String getDate() {
        return date;
    }

    public String getOptionType() {
        return optionType;
    }

    public String getOptionValue() {
        return optionValue;
    }


    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setOptionPrice(Double optionPrice) {
        this.optionPrice = optionPrice;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }


}
