package com.example.datasaveandloadmvp.model;

/**
 * Created by Guure on 2017/4/22.
 */

public class Data {

    private int id;
    private String firstName;
    private String lastName;

    public Data() {
        super();
    }

    public Data(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

}
