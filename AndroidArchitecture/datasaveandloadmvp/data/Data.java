package com.example.datasaveandloadmvp.data;

/**
 * Created by Guure on 2017/4/22.
 * Reference: https://github.com/youyuge34/MVPTest_login
 */

public class Data {

    private String firstName;
    private String lastName;

    public Data(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
