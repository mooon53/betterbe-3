package model;

import org.json.JSONObject;

public class Person {
    private String username;
    private String fname;
    private String surname;
    private String email;


    public Person(){}

    public Person(String username, String fname, String surname, String email){
        this.username = username;
        this.fname = fname;
        this.surname = surname;
        this.email = email;

    }

    public JSONObject toJSON() {
        JSONObject response = new JSONObject();
        response.put("username", username);
        response.put("fname", fname);
        response.put("surname", surname);
        response.put("email", email);

        return response;
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


}
