package com.ironhack.classes;

import java.util.Objects;

public class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    private final int id;
    private static int count = 0; //The system will automatically create an id for the Contact

    //Constructor
    public Contact(String name, String phoneNumber, String email, String companyName) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.count ++;
        this.id = count;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    ////////////////////////////////GETTERS AND SETTERS//////////////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public String toStringFormatted() {
        return "Name: " + name +
                " | Phone Number: " + phoneNumber  +
                " | Email: " + email +
                " | Company Name: " + companyName;
    }
}

