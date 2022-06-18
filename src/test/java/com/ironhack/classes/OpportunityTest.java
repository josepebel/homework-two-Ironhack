package com.ironhack.classes;

import com.ironhack.enums.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpportunityTest {
    private static Contact contact;

    private static Opportunity opportunity1;
    private static Opportunity opportunity2;
    private static Opportunity opportunity3;

    @BeforeAll
    static void setUp() {
        contact = new Contact("David","+34 6666666","dadsd@gmail.com","Lexus");

        opportunity1 = new Opportunity(Product.HYBRID,5,contact);
        opportunity2 = new Opportunity(Product.BOX,3,contact);
        opportunity3 = new Opportunity(Product.FLATBED, 1,contact);
    }


    @Test
    void getId_idDifferentFromCount() {

        assertNotEquals(Opportunity.getCount(),opportunity2.getId());
        assertNotEquals(Opportunity.getCount(), opportunity1.getId());
    }

    @Test
    void getId_correctId() {

        assertEquals(1,opportunity1.getId());
        assertEquals(2,opportunity2.getId());
        assertEquals(3,opportunity3.getId());

    }

    @Test
    void getCount_countStatic() {

        assertEquals(3,Opportunity.getCount());

    }
}
