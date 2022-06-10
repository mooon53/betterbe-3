package model;

public class Account {
    private String username;
    private String password;
    private Boolean employee;
    private Boolean loggedIn;

    public Account(){}

    public Account(String username, String password, Boolean employee){
        this.username = username;
        this.password = password;
        this.employee = employee;
        this.loggedIn = false;
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

    public Boolean getLoggedIn() {
        return loggedIn;
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

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
