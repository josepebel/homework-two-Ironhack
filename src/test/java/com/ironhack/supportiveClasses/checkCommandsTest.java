package com.ironhack.supportiveClasses;

import com.ironhack.classes.Contact;
import com.ironhack.classes.Lead;
import com.ironhack.classes.Opportunity;
import com.ironhack.enums.Product;
import com.ironhack.exceptions.ExistentElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class checkCommandsTest {
    private static Lead lead1;
    private static Lead lead2;

    private static Opportunity opportunity1;
    private static Opportunity opportunity2;
    private static Contact contact;
    private static CheckCommands checkCommands = new CheckCommands();

    @BeforeAll
    static void setUp() {
        lead1 = new Lead("Juan Lopez", "666555444", "pipo@popi.pop", "qué calor");
        lead2 = new Lead("Paco Diaz", "633222111", "paco@paco.com", "no me entero");
        try {
            checkCommands.getInteractions().addLead(lead1);
            checkCommands.getInteractions().addLead(lead2);
        } catch (ExistentElementException e) {
            e.printStackTrace();
        }
        
        contact = new Contact("David", "+34 6666666", "dadsd@gmail.com", "Lexus");
        opportunity1 = new Opportunity(Product.HYBRID, 5, contact);
        opportunity2 = new Opportunity(Product.HYBRID, 5, contact);
        // checkCommands.getInteractions().createOpportunity(opportunity);
        try {
            checkCommands.getInteractions().addOpportunity(opportunity1);
            checkCommands.getInteractions().addOpportunity(opportunity2);
        } catch (ExistentElementException e) {
            e.printStackTrace();
        }
    }


    @Test
    void checkCommand_correctCommand_DoesNotThrowError() {
        //show leads
        assertDoesNotThrow(() -> checkCommands.checkCommand("show leads"));

        //lookup lead
        assertDoesNotThrow(() -> checkCommands.checkCommand("lookup lead 1"));

        //show opportunities
        assertDoesNotThrow(() -> checkCommands.checkCommand("show opportunities"));

        //lookup opportunity
        assertDoesNotThrow(() -> checkCommands.checkCommand("lookup opportunity 1"));

        //close-won id
        assertDoesNotThrow(() -> checkCommands.checkCommand("close-won 1"));
        assertDoesNotThrow(() -> checkCommands.checkCommand(" close-won 1 "));

        //close-lost id
        assertDoesNotThrow(() -> checkCommands.checkCommand("close-lost 2"));
        assertDoesNotThrow(() -> checkCommands.checkCommand("close-lost 2 "));
    }

    @Test
    void checkCommand_incorrectCommand_ThrowError() {
        //new lead
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("new-lead"));
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("newlead"));

        //show leads
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("show lead"));
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("show-leads"));

        //lookup lead
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("lookup 1"));
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("lookuplead 1"));

        //show opportunities
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("show opportunitie"));
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("show oportunities"));

        //lookup opportunity
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("lookup 1"));
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("lookup-opportunity"));
        
        //close-won id
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("close won 1"));
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("close 1"));

        //close-lost id
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("close lost 1"));
        assertThrows(IllegalArgumentException.class,() -> checkCommands.checkCommand("close-lost1"));
    }

    @Test
    void checkCommand_incorrectCommandId_ThrowError() {  
        //lookup lead
        checkCommands.checkCommand("lookup lead asasa");
        assertEquals(-3, checkCommands.getState());

        //convert
        checkCommands.checkCommand("convert asasa");
        assertEquals(-4, checkCommands.getState());


        //lookup opportunity
        checkCommands.checkCommand("lookup opportunity asasa");
        assertEquals(-7, checkCommands.getState());

        //close-lost id
        checkCommands.checkCommand("close-lost asasa");
        assertEquals(-9, checkCommands.getState());

        //close-won id
        checkCommands.checkCommand("close-won asasa");
        assertEquals(-10, checkCommands.getState());


    }
}
