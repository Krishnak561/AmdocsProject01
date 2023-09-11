package com.amdocs.entities;

import java.util.*;

public class CustomerEntity extends Object{

    static Scanner sc = new Scanner(System.in);

    private int pincode;

    private String firstName, lastName, cityOfResidence, email, phoneNum, dob;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }


    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCityOfResidence() {
        return cityOfResidence;
    }

    public void setCityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "CustomerRecord [pincode=" + pincode + ", dob=" + dob + ", firstName=" + firstName + ", lastName="
                + lastName + ", cityOfResidence=" + cityOfResidence + ", email=" + email + ", phoneNum=" + phoneNum
                + "]";
    }

    
}
