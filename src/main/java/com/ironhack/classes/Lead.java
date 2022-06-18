package com.ironhack.classes;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Lead {
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    private static int count = 0;
    private final int id;

    public Lead(String name, String phoneNumber, String email, String companyName) {
        this.name = name;
        setPhoneNumber(phoneNumber);
        setEmail(email);
        this.companyName = companyName;
        this.count ++;
        this.id = count;
    }

    public Contact convertLead(){
        Contact contact = new Contact(getName(), getPhoneNumber(), getEmail(), getCompanyName());
        return contact;
    }

    public static String getNameFromUser(){
        Scanner scanner = new Scanner(System.in);
        String name = "";
        try {

            name = scanner.nextLine();
        } catch (InputMismatchException e){
            name= "";
        }

        return name;
    }

    public static String getPhoneNumberFromUser(){
        Scanner scanner = new Scanner(System.in);
        String phoneNumber = "";
        try{
            phoneNumber = scanner.nextLine();
        } catch (InputMismatchException e){
            phoneNumber= "";
        }

        return phoneNumber;
    }

    public static String getEmailFromUser(){
        Scanner scanner = new Scanner(System.in);
        String email = "";
        try{
            email = scanner.nextLine();
        } catch (InputMismatchException e){
            email= "";
        }

        return email;
    }

    public static String getCompanyNameFromUser(){
        Scanner scanner = new Scanner(System.in);
        String companyName = "";
        try {

            companyName = scanner.nextLine();
        } catch (InputMismatchException e){
            companyName= "";
        }

        return companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lead lead = (Lead) o;
        return id == lead.id;
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

    public static String regexPhoneNumberValidation(){
        String validation = "^[+]*[(]{0,1}[+]*[0-9]{1,4}[)]{0,1}[0-9]{8,9}";
        return validation;
    }

    public Boolean setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches(regexPhoneNumberValidation())) {
            this.phoneNumber = phoneNumber;
            return true;
        } else {
            throw new IllegalArgumentException("Phone number format should be valid.");
        }
    }

    public String getEmail() {
        return email;
    }

    public static String regexEmailValidation(){
        String validation = "(?i:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\" +
                "x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
                "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4]" +
                "[0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\" +
                "x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return validation;
    }

    public boolean setEmail(String email) {
//      RFC 5322 Official Standard
        if (email.matches(regexEmailValidation())) {
            this.email = email;
            return true;
        } else {
            throw new IllegalArgumentException("Email format should be valid");
        }
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

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                ", id=" + id +
                '}';
    }

    public String toStringFormatted() {
        return  "ID " + id +
                " | Name: '" + name + '\'' +
                " | Phone Number: '" + phoneNumber + '\'' +
                " | Email: '" + email + '\'' +
                " | Company Name: '" + companyName + '\'';
    }
}
