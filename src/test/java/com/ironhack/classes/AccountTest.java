package com.ironhack.classes;

import com.ironhack.enums.Industry;
import com.ironhack.enums.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    // private List<Contact> contactList;
    // private List<Opportunity> opportunityList;
    private HashMap<Integer, Contact> contactList = new HashMap<>();
    private HashMap<Integer, Opportunity> opportunityList = new HashMap<>();
    private Contact contact;
    private Opportunity opportunity;


    @BeforeEach
    void SetUp() {

        Contact contact = new Contact("Jose","+34 655258555","test@mail.com","Seat");
        Opportunity opportunity = new Opportunity(Product.HYBRID,5, contact);

        contactList.put(contact.getId(), contact);
        opportunityList.put(opportunity.getId(), opportunity);
    //    contactList = List.of(contact);
    //    opportunityList = List.of(opportunity);
    }

    @Test
    void getId_accounts_idMatch() {

        Account account1 = new Account(Industry.ECOMMERCE,4,"Barcelona","España", contactList, opportunityList);
        Account account2 = new Account(Industry.MANUFACTURING,2,"Madrid","España", contactList, opportunityList);
        assertEquals(0, account1.getId());
        assertEquals(1, account2.getId());

    }

    @Test
    void getCount_accounts_numberAccounts() {

        Account account3 = new Account(Industry.MANUFACTURING,4,"Barcelona","España", contactList, opportunityList);
        assertEquals(3, account3.getCount());

        Account account4 = new Account(Industry.ECOMMERCE,2,"Madrid","España", contactList, opportunityList);
        Account account5 = new Account(Industry.MEDICAL,4,"Barcelona","España", contactList, opportunityList);
        Account account6= new Account(Industry.OTHER,2,"Madrid","España", contactList, opportunityList);

        assertEquals(6, account6.getCount());

    }
}