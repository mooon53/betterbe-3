package model;

public class Account {
    private String username;
    private String password;
    private Boolean employee;

    public Account(){}

    public Account(String username, String password, Boolean employee){
        this.username = username;
        this.password = password;
        this.employee = employee;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEmployee() {
        return employee;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployee(Boolean employee) {
        this.employee = employee;
    }
}
