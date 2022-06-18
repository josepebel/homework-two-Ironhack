package com.ironhack.classes;

import com.ironhack.enums.Industry;
import com.ironhack.exceptions.ExistentElementException;
import com.ironhack.supportiveClasses.PrintText;

import java.security.InvalidParameterException;
import java.util.*;

public class Account {

    // Variable static para asignar un id automático

    private static int count = 0;

    private final int id;
    private Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    private HashMap<Integer, Contact> contacts;
    private HashMap<Integer, Opportunity> opportunities;

    // Constructor

    public Account(Industry industry, int employeeCount, String city, String country, HashMap<Integer, Contact> contacts, HashMap<Integer, Opportunity> opportunities) {
        count++;
        this.id = count;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contacts = contacts;
        this.opportunities = opportunities;
    }

    public Account(Industry industry, int employeeCount, String city, String country, Contact contact, Opportunity opportunity) throws ExistentElementException {
        count ++;
        this.id = count;
        this.industry = industry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        this.contacts = new HashMap<>();
        addContact(contact);
        this.opportunities = new HashMap<>();
        addOpportunity(opportunity);
    }

    public static int getNumberOfEmployees() {

        Scanner scanner = new Scanner(System.in);
        int numberOfEmployees = 0;
        try {
            String lectorComando = scanner.nextLine();
            numberOfEmployees = Integer.parseInt(lectorComando);
        } catch (InputMismatchException e){
            numberOfEmployees = 0;
        }
        catch (NumberFormatException e){
            numberOfEmployees = 0;
        }
        if (numberOfEmployees < 1) {
            PrintText.wrongNumberOfEmployees();
            numberOfEmployees = 0;
        }
        return numberOfEmployees;
    }

    public Contact findContact(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (contacts.containsKey(id)) {
            Contact contact = contacts.get(id);
            return contact;
        }
        else
            throw new NoSuchElementException("The id " + id + " does not match with any contact");
    }

    public void removeContact(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (contacts.containsKey(id)){
            contacts.remove(id);
        } else{
            throw new NoSuchElementException("The id " + id + " does not match with any contact");
        }
    }

    public void addContact(Contact contact) throws ExistentElementException {
        if (contact.getId() < 1)
            throw new InvalidParameterException();
        if (contacts.containsKey(contact.getId())){
            throw new ExistentElementException("The id " + contact.getId() + " already exists in the contacts BBDD");
        } else{
            this.contacts.put(contact.getId(), contact);
        }
    }

    public Opportunity findOpportunity(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (opportunities.containsKey(id)) {
            Opportunity opportunity = opportunities.get(id);
            return opportunity;
        }
        else
            throw new NoSuchElementException("The id " + id + " does not match with any opportunity");
    }

    public void removeOpportunity(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (opportunities.containsKey(id)){
            opportunities.remove(id);
        } else{
            throw new NoSuchElementException("The id " + id + " does not match with any opportunity");
        }
    }

    public void addOpportunity(Opportunity opportunity) throws ExistentElementException {
        if (opportunity.getId() < 1)
            throw new InvalidParameterException();
        if (opportunities.containsKey(opportunity.getId())){
            throw new ExistentElementException("The id " + opportunity.getId() + " already exists in the opportunities BBDD");
        } else{
            this.opportunities.put(opportunity.getId(), opportunity);
        }
    }

    ////////////////////////////////GETTERS AND SETTERS//////////////////////


    public int getId() {
        return id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public HashMap<Integer, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(HashMap<Integer, Contact> contacts) {
        this.contacts = contacts;
    }

    public HashMap<Integer, Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(HashMap<Integer, Opportunity> opportunities) {
        this.opportunities = opportunities;
    }

    public static int getCount() {
        return count;
    }

    // To String

    @Override
    public String toString() {
        return "Account{" +
                ", industry=" + industry +
                ", employeeCount=" + employeeCount +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", contacts=" + contacts +
                ", opportunities=" + opportunities +
                '}';
    }

    public String toStringFormatted() {
        return  "Account ID " + id +
                " | Industry: " + industry +
                " | Employee Count: " + employeeCount +
                " | City: " + city +
                " | Country: '" + country +
                " | Contacts: " + contacts +
                " | Opportunities: " + opportunities;
    }

    public String toStringFormattedWithoutOpportunityAndContact() {
        return  "Account ID " + id +
                " | Industry: " + industry +
                " | Employee Count: " + employeeCount +
                " | City: " + city +
                " | Country: " + country;
    }
}
