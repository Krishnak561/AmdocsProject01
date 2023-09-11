package com.amdocs.entities;

public class AppointmentEntity {
    private String FirstName, LastName, reasonOfVisit, dateOfVisit;
    private int customer_ID;

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getReasonOfVisit() {
        return reasonOfVisit;
    }

    public void setReasonOfVisit(String reasonOfVisit) {
        this.reasonOfVisit = reasonOfVisit;
    }
}
