package model;

public class Rule {
    Long[] options;
    Boolean exclusive;
    Boolean mandatory;
    Long carId;

    public Rule() {}

    public Rule(Long[] options, Boolean exclusive, Boolean mandatory, Long carId) {
        this.options = options;
        this.exclusive = exclusive;
        this.mandatory = mandatory;
        this.carId = carId;
    }

    public Long[] getOptions() {
        return options;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public Boolean getMandatory() {
        return mandatory;
    }
}
