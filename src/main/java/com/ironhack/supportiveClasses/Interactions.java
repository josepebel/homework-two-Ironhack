package com.ironhack.supportiveClasses;

import com.ironhack.classes.Account;
import com.ironhack.classes.Contact;
import com.ironhack.classes.Lead;
import com.ironhack.classes.Opportunity;
import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;
import com.ironhack.enums.Status;
import com.ironhack.exceptions.ExistentElementException;

import java.security.InvalidParameterException;
import java.util.*;

public class Interactions {

    private HashMap<Integer, Opportunity> opportunities;
    private HashMap<Integer, Lead> leads;
    private HashMap<Integer, Contact> contacts;
    private HashMap<Integer, Account> accounts;

    public Interactions() {
        opportunities = new HashMap<>();
        leads = new HashMap<>();
        contacts = new HashMap<>();
        accounts = new HashMap<>();
    }

    ///////////MÉTODOS PARA MANIPULAR LOS HASHMAPS//////////////
    public Opportunity findOpportunity(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (opportunities.containsKey(id)) {
            Opportunity opportunity = opportunities.get(id);
            return opportunity;
        }
        else
            throw new NoSuchElementException("The ID " + id + " does not match with any opportunity");
    }

    //De momento no se usa, creada para posibles extensiones futuras
    public void removeOpportunity(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (opportunities.containsKey(id)){
            //Según el diagrama de clases, cuando se borra una oportunidad se borra su decisionMaker
            removeContact(opportunities.get(id).getDecisionMaker().getId());
            opportunities.remove(id);
        } else{
            throw new NoSuchElementException("The ID " + id + " does not match with any opportunity");
        }
    }

    public void addOpportunity(Opportunity opportunity) throws ExistentElementException {
        if (opportunity.getId() < 1)
            throw new InvalidParameterException();
        if (opportunities.containsKey(opportunity.getId())){
            throw new ExistentElementException("The ID " + opportunity.getId() + " already exists in the opportunities DB");
        } else{
            this.opportunities.put(opportunity.getId(), opportunity);
        }
    }

    public Lead findLead(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (leads.containsKey(id)) {
            Lead lead = leads.get(id);
            return lead;
        }
        else
            throw new NoSuchElementException("The ID " + id + " does not match with any lead");
    }

    public void removeLead(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (leads.containsKey(id)){
            leads.remove(id);
        } else{
            throw new NoSuchElementException("The ID " + id + " does not match with any lead");
        }
    }

    public void addLead(Lead lead) throws InvalidParameterException, ExistentElementException {
        if (lead.getId() < 1)
            throw new InvalidParameterException();
        if (leads.containsKey(lead.getId())){
            throw new ExistentElementException("The ID " + lead.getId() + " already exists in the leads DB");
        } else{
            this.leads.put(lead.getId(), lead);
        }
    }

    public Contact findContact(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (contacts.containsKey(id)) {
            Contact contact = contacts.get(id);
            return contact;
        }
        else
            throw new NoSuchElementException("The ID " + id + " does not match with any contact");
    }

    public void removeContact(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (contacts.containsKey(id)){
            contacts.remove(id);
        } else{
            throw new NoSuchElementException("The ID " + id + " does not match with any contact");
        }
    }

    public void addContact(Contact contact) throws ExistentElementException {
        if (contact.getId() < 1)
            throw new InvalidParameterException();
        if (contacts.containsKey(contact.getId())){
            throw new ExistentElementException("The ID " + contact.getId() + " already exists in the contacts DB");
        } else{
            this.contacts.put(contact.getId(), contact);
        }
    }

    public Account findAccount(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (accounts.containsKey(id)) {
            Account account = accounts.get(id);
            return account;
        }
        else
            throw new NoSuchElementException("The id " + id + " does not match with any account");
    }

    public Account findAccountByOpportunity(Opportunity opportunity) throws NoSuchElementException {

            for(Account account: accounts.values()){
                if (account.getOpportunities().containsKey(opportunity.getId())){
                    return account;
                }
            }
            throw new NoSuchElementException("The Opportunity " + opportunity + " does not match with any account");

   }




    //De momento no se usa, creada para posibles extensiones futuras
    public void removeAccount(int id) throws InvalidParameterException, NoSuchElementException {
        if (id < 1)
            throw new InvalidParameterException();
        if (accounts.containsKey(id)){

            //Según el diagrama de clases, cuando se borra una account se borran sus contacts
            HashMap<Integer, Contact> contacts  = accounts.get(id).getContacts();
            for (Integer idContact : contacts.keySet()){
                removeContact(contacts.get(idContact).getId());
            }

            //Según el diagrama de clases, cuando se borra una account se borran sus oportunities
            HashMap<Integer, Opportunity> opportunities  = accounts.get(id).getOpportunities();
            for (Integer idOpportunity : opportunities.keySet()){
                removeContact(opportunities.get(idOpportunity).getId());
            }

            accounts.remove(id);
        } else{
            throw new NoSuchElementException("The ID " + id + " does not match with any account");
        }
    }

    public void addAccount(Account account) throws ExistentElementException {
        if (account.getId() < 1)
            throw new InvalidParameterException();
        if (accounts.containsKey(account.getId())){
            throw new ExistentElementException("The ID " + account.getId() + " already exists in the accounts BBDD");
        } else{
            this.accounts.put(account.getId(), account);
        }
    }

    ///////////MÉTODOS USADOS DESDE COMANDOS//////////////
    public void createLead(){

        //get name from user
        String name = "";
        do{
            PrintText.askLeadName();
            name = Lead.getNameFromUser();

            if ("".equals(name))
                PrintText.invalidLeadName();
        }while ("".equals(name));

        //get phone number
        String phoneNumber = "";
        do{
            PrintText.askPhoneNumber();
            String initialNumber = Lead.getPhoneNumberFromUser();
            if(initialNumber.matches(Lead.regexPhoneNumberValidation())){
                phoneNumber = initialNumber;
            } else {
                phoneNumber = "";
                PrintText.invalidPhoneNumber();
            }
        } while ("".equals(phoneNumber));

        //get email
        String email = "";
        do{
            PrintText.askEmail();
            String initialEmail = Lead.getEmailFromUser();
            if(initialEmail.matches(Lead.regexEmailValidation())){
                email = initialEmail;
            } else {
                email = "";
                PrintText.invalidEmail();
            }
        } while ("".equals(email));

        //get company Name
        String companyName = "";
        do{
            PrintText.askCompanyName();
            companyName = Lead.getCompanyNameFromUser();

        }while ("".equals(companyName));

        Lead newLead = new Lead(name,phoneNumber,email,companyName);

        try {
            addLead(newLead);
            PrintText.leadCreated(newLead);
        }
        catch (InvalidParameterException e) {
            PrintText.wrongIdFormat();
        } catch (ExistentElementException e) {
            PrintText.iDAlreadyExists(e.getMessage());
        }

    }

    public void showLeads(){
        if (leads.size() == 0){
            PrintText.noLeadsYet();
        } else {
            PrintText.headerShowLeads();
            for (Integer id : leads.keySet()){
                System.out.println("    ID " + id + "  |  Name: " + leads.get(id).getName());
                System.out.println(Colors.BG_WHITE);
            }
        }
    }

    public void lookupLead(int id){
        try{
            PrintText.printLead(findLead(id));
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
            PrintText.idDoesNotExist();
            PrintText.recommendationShowLeads();
        }
    }

    public void convertLead(int id) {

        try {
            Lead lead = findLead(id);

            //Create the contact from the lead
            Contact contact = lead.convertLead();

            Opportunity opportunity = obtainOpportunity(contact);

            Account account = obtainAccount(contact, opportunity);

            removeLead(id);
            addOpportunity(opportunity);
            addContact(contact);
            addAccount(account);

        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
            PrintText.idDoesNotExist();
            PrintText.recommendationShowLeads();
        } catch (ExistentElementException e) {
            PrintText.iDAlreadyExists(e.getMessage());
        }
    }

    public void showOpportunities(){
        if (opportunities.size() == 0){
            PrintText.noOpportunitiesYet();
        } else {
            PrintText.headerShowOpportunities();
            for (Integer id : opportunities.keySet()){
                System.out.println(opportunities.get(id).toStringFormatted());
                System.out.println(Colors.BG_WHITE);
            }
        }
    }

    public void lookupOpportunity(int id){
        try{
            Opportunity opportunity = findOpportunity(id);
            PrintText.printOpportunity(opportunity);
            Account account = findAccountByOpportunity(opportunity);
            lookupAccountByOpportunity(account);
            showContactsByAccount(account);
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
            PrintText.idDoesNotExist();
            PrintText.recommendationShowOpportunities();
        }
    }

    public int closeLost (int id)  {
        try{
            Opportunity opportunity = findOpportunity(id);
            if (opportunity.getStatus().equals(Status.OPEN)) {
                opportunity.setStatus(Status.CLOSED_LOST);
                PrintText.closeOpportunityLost(id);
                return 9;
            } else {
                PrintText.opportunityAlreadyClosed(id);
                return -9;
            }
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
            return -9;
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
            PrintText.idDoesNotExist();
            PrintText.recommendationShowOpportunities();
            return -9;
        }
    }

    public int closeWon (int id)  {
        try{
            Opportunity opportunity = findOpportunity(id);
            if (opportunity.getStatus().equals(Status.OPEN)) {
                opportunity.setStatus(Status.CLOSED_WON);
                PrintText.closeOpportunityWon(id);
                return 10;
            } else {
                PrintText.opportunityAlreadyClosed(id);
                return -10;
            }
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
            return -10;
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
            PrintText.idDoesNotExist();
            PrintText.recommendationShowOpportunities();
            return -10;
        }
    }


    //Esta función se utiliza en convertLead
    public static Opportunity obtainOpportunity(Contact contact){
        //Get product from user
        Product product = null;
        do {

            PrintText.askProduct();
            product = Product.getProductFromScanner();

        } while (product == null);

        //Get number of trucks from user
        int numberOfTrucks = 0;
        do {

            PrintText.askNumberOfTrucks();
            numberOfTrucks = Opportunity.getNumberOfTrucks();

        } while (numberOfTrucks == 0);

        //Create the opportunity from the lead
        Opportunity opportunity = new Opportunity(product, numberOfTrucks, contact);

        return opportunity;

    }

    //Esta función se utiliza en convertLead y su excepción es tratada allí
    public static Account obtainAccount(Contact contact, Opportunity opportunity) throws ExistentElementException {

        //Get industry
        Industry industry = null;
        do {

            PrintText.askIndustry();
            industry = Industry.getIndustryFromScanner();

        } while (industry == null);

        //Get number of employees
        int numberOfEmployees = 0;
        do {

            PrintText.askNumberOfEmployees();
            numberOfEmployees = Account.getNumberOfEmployees();

        } while (numberOfEmployees == 0);

        Scanner scanner = new Scanner(System.in);

        //Get city
        String city = null;
        PrintText.askCity();
        city = scanner.nextLine();

        //Get country
        String country = null;
        PrintText.askCountry();
        country = scanner.nextLine();

        Account account = new Account(industry, numberOfEmployees, city, country, contact, opportunity);

        return account;
    }




    //De momento no se usa, creada para posibles extensiones futuras
    public void showAccounts(){
        if (accounts.size() == 0){
            PrintText.noAccountsYet();
        } else {
            PrintText.headerShowAccounts();
            for (Integer id : accounts.keySet()){
                System.out.println("ID " + id);
                System.out.println("Account: " + accounts.get(id).toStringFormatted());
            }
        }
    }

    //De momento no se usa, creada para posibles extensiones futuras
    public void showContacts(){
        if (contacts.size() == 0){
            PrintText.noAccountsYet();
        } else {
            PrintText.headerShowContacts();
            for (Integer id : contacts.keySet()){
                System.out.println("ID " + id);
                System.out.println("Contact: " + contacts.get(id).toStringFormatted());
            }
        }
    }

    //De momento no se usa, creada para posibles extensiones futuras
    public void lookupAccount(int id){
        try{
            PrintText.printAccount(findAccount(id));
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
        }
    }

    public void lookupAccountByOpportunity(Account account){
        try{
            PrintText.printAccountWithoutOpportunity(account);
        } catch (InvalidParameterException e){
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e){
            PrintText.noIdFound(e.getMessage());
        }
    }

    public void showContactsByAccount(Account account){
        for (Contact contact : account.getContacts().values()){
            PrintText.printContact(contact);
        }
    }


    //De momento no se usa, creada para posibles extensiones futuras
    public void lookupContact(int id) {
        try {
            PrintText.printContact(findContact(id));
        } catch (InvalidParameterException e) {
            PrintText.wrongIdFormat();
        } catch (NoSuchElementException e) {
            PrintText.noIdFound(e.getMessage());
        }
    }


        ////////////////////////////////GETTERS AND SETTERS//////////////////////
    public HashMap<Integer, Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(HashMap<Integer, Opportunity> opportunities) {
        this.opportunities = opportunities;
    }

    public HashMap<Integer, Lead> getLeads() {
        return leads;
    }

    public void setLeads(HashMap<Integer, Lead> leads) {
        this.leads = leads;
    }

    public HashMap<Integer, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(HashMap<Integer, Contact> contacts) {
        this.contacts = contacts;
    }

    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<Integer, Account> accounts) {
        this.accounts = accounts;
    }
}
