package model;

public class Person {
    private String username;
    private String fname;
    private String surname;
    private String email;
    private String phone;

    public Person(){}

    public Person(String username, String fname, String surname, String email, String phone){
        this.username = username;
        this.fname = fname;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getFname() {
        return fname;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
