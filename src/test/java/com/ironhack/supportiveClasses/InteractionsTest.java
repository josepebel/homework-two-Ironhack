package com.ironhack.supportiveClasses;

import com.ironhack.classes.Account;
import com.ironhack.classes.Contact;
import com.ironhack.classes.Lead;
import com.ironhack.classes.Opportunity;
import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;
import com.ironhack.enums.Status;
import com.ironhack.exceptions.ExistentElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class InteractionsTest {

    private static Interactions interactions = new Interactions();
    private static Opportunity opportunity1;
    private static Opportunity opportunity2;
    private static Account account1;
    private static Account account2;
    private static Contact contact1;
    private static Contact contact2;
    private static Lead lead1;
    private static Lead lead2;


    @BeforeAll
    static void setUp() throws ExistentElementException {
        contact1 = new Contact("David","6666666","dadsd@gmail.com","Lexus");
        contact2 = new Contact("Manuel", "567 984 567", "manu_ca@gmail.com", "BT");
        try {
            interactions.addContact(contact1);
            interactions.addContact(contact2);
        } catch (ExistentElementException e) {
            e.printStackTrace();
        }
        
        opportunity1 = new Opportunity(Product.HYBRID,5,contact1);
        opportunity2 = new Opportunity(Product.HYBRID,6,contact2);
        // interactions.createOpportunity(opportunity);
        try {
            interactions.addOpportunity(opportunity1);
            interactions.addOpportunity(opportunity2);
        } catch (ExistentElementException e) {
            e.printStackTrace();
        }
        
        account1 = new Account(Industry.ECOMMERCE, 23, "Barcelona", "España", contact1, opportunity1);
        account2 = new Account(Industry.MANUFACTURING, 40, "Madrid", "España", contact2, opportunity2);
        try {
            interactions.addAccount(account1);
            interactions.addAccount(account2);
        } catch (ExistentElementException e) {
            e.printStackTrace();
        }

        lead1 = new Lead("Juan Alonso-Rivas", "876543210", "pepitopulgarcito@jotmeil.com", "Seat");
        lead2 = new Lead("Pepe Pérez-García", "876543210", "pepitopulgarcito@jotmeil.com", "Renault");
        try {
            interactions.addLead(lead1);
        } catch (ExistentElementException e) {
            e.printStackTrace();
        }
        try {
            interactions.addLead(lead2);
        } catch (ExistentElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    void closeLost_launchClosedByID_setClosed() {
        System.out.println("Algo va a pasar");
        interactions.closeLost(opportunity1.getId());
        System.out.println("Algo está pasando");
        assertEquals(Status.CLOSED_LOST,opportunity1.getStatus());
        System.out.println("Algo ha pasado");
    }

    @Test
    void closeWon_launchClosedByID_setClosed() {
        interactions.closeWon(opportunity2.getId());
        assertEquals(Status.CLOSED_WON,opportunity2.getStatus());
    }

    @Test
    void closeLost_launchNotExistingID_throwError() {
        assertEquals(-9, interactions.closeLost(5));
        assertEquals(-9, interactions.closeLost(25));
    }

    @Test
    void closeWon_launchNotExistingID_throwError() {
        assertEquals(-10, interactions.closeWon(5));
        assertEquals(-10, interactions.closeWon(25));
    }
    
    //addOpportunity
    @Test
    void addOpportunity_validOpportunity_opportunityAdded() throws ExistentElementException {
        Opportunity opportunity3 = new Opportunity(Product.HYBRID, 23, contact1);
        interactions.addOpportunity(opportunity3);

        assertEquals(3, interactions.getOpportunities().size());
    }

    @Test
    void addOpportunity_opportunityAlreadyCreated_ExistingElementException() {
        assertThrows(ExistentElementException.class,() -> interactions.addOpportunity(opportunity1));
    }

    //addAccount
    @Test
    void addAccount_validAccount_accountAdded() throws ExistentElementException {
        Account account3 = new Account(Industry.MEDICAL, 45, "Málaga", "España", contact2, opportunity2);
        interactions.addAccount(account3);

        assertEquals(3, interactions.getAccounts().size());
    }

    @Test
    void addAccount_accountAlreadyCreated_ExistingElementException() {
        assertThrows(ExistentElementException.class,() -> interactions.addAccount(account2));
    }

    //addLead
    @Test
    void addLead_validContact_leadAdded() throws ExistentElementException {
        Lead lead3 = new Lead("Mónica", "657896012", "moni_ca@gmail.com", "Noshe");
        interactions.addLead(lead3);

        assertEquals(3, interactions.getLeads().size());
    }

    @Test
    void addLead_leadAlreadyCreated_ExistingElementException() {
        assertThrows(ExistentElementException.class,() -> interactions.addLead(lead1));
    }

    //removeLead
    @Test
    void removeLead_validID_ListRemoved() {
        interactions.removeLead(2);

        assertEquals(2, interactions.getLeads().size());
    }

    @Test
    void removeLead_launchNotExistingID_throwError() {
        assertThrows(NoSuchElementException.class,() ->interactions.removeLead(4));
        assertThrows(NoSuchElementException.class,() ->interactions.removeLead(8));
    }
    
    //addContact
    @Test
    void addContact_validContact_contactAdded() throws ExistentElementException {
        Contact contact3 = new Contact("Miriam", "678 912 345", "mi_ri_am@gmail.com", "LU");
        interactions.addContact(contact3);

        assertEquals(3, interactions.getContacts().size());
    }

    @Test
    void addContact_contactAlreadyCreated_ExistingElementException() {
        assertThrows(ExistentElementException.class,() -> interactions.addContact(contact2));
    }

    //removeContact
    @Test
    void removeContact_validContact_contactRemoved() {
        interactions.removeContact(contact1.getId());

        assertEquals(2, interactions.getContacts().size());
    }

    @Test
    void removeContact_noExistingId_NoSuchElementException() {
        assertThrows(NoSuchElementException.class,() -> interactions.removeContact(contact1.getId()));
    }


    //addOpportunity
    @Test
    void findlead_validLead_foundLead() {
        Lead foundLead = interactions.findLead(lead1.getId());
        assertEquals(lead1.getId(), foundLead.getId());
    }

    @Test
    void findlead_validLead_notFoundLead() {
        assertThrows(NoSuchElementException.class,() -> interactions.findLead(99));
    }

    @Test
    void findlead_notValidLead_throwsException() {
        assertThrows(InvalidParameterException.class,() -> interactions.findLead(-1));
    }

    @Test
    void findOpportunity_validOpportunity_foundOpportunity() {
        Opportunity foundOpportunity = interactions.findOpportunity(opportunity1.getId());
        assertEquals(opportunity1.getId(), foundOpportunity.getId());
        assertEquals(5,foundOpportunity.getQuantity());
    }

    @Test
    void findOpportunity_validOpportunity_notFoundOpportunity() {
        assertThrows(NoSuchElementException.class,() -> interactions.findOpportunity(99));
    }

    @Test
    void findOpportunity_notValidOpportunity_throwsException() {
        assertThrows(InvalidParameterException.class,() -> interactions.findOpportunity(-1));
    }

    @Test
    void findContact_validContact_foundContact() {
        Contact foundContact = interactions.findContact(contact1.getId());
        assertEquals(contact1.getId(), foundContact.getId());
        assertEquals("David", foundContact.getName());
    }

    @Test
    void findContact_validContact_notFoundContact() {
        assertThrows(NoSuchElementException.class,() -> interactions.findContact(50));
    }

    @Test
    void findContact_notValidContact_throwsException() {
        assertThrows(InvalidParameterException.class,() -> interactions.findContact(-1));
    }



    @Test
    void findAccount_validAccount_foundAccount() {
        Account foundAccount = interactions.findAccount(account1.getId());
        assertEquals(account1.getId(), foundAccount.getId());
        assertEquals(contact1, foundAccount.getContacts().get(foundAccount.getId()));
        assertEquals(23, foundAccount.getEmployeeCount());
    }

    @Test
    void findAccount_validAccount_notFoundAccount() {
        assertThrows(NoSuchElementException.class,() -> interactions.findAccount(5));
    }

    @Test
    void findAccount_notValidAccount_throwsException() {
        assertThrows(InvalidParameterException.class,() -> interactions.findAccount(-1));
    }

    @Test
    void findAccountByOpportunity_convertLead_returnAccount() {
        Account accountFound = interactions.findAccountByOpportunity(opportunity1);
        assertEquals(account1, accountFound);
    }
    
}