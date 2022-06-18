package com.ironhack.classes;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LeadTest {

    private Lead lead;

    @BeforeEach
    void setUp() {
        lead = new Lead("Pepe Pérez-García", "876543210", "pepitopulgarcito@jotmeil.com", "Ay, no me des");
    }

    @Test
    @Order(1)
    void getCount_ShouldIncreaseCountWhenNewInstanceCreated() {
        assertEquals(1, lead.getCount());
        Lead lead2 = new Lead("Juan Lopez", "666555444", "pipo@popi.pop", "qué calor");
        assertEquals(2, lead.getCount());
        Lead lead3 = new Lead("Paco Diaz", "633222111", "paco@paco.com", "no me entero");
        assertEquals(3, lead.getCount());
    }

    @Test
    void getId_ShouldMatchCountAndId() {
        assertEquals(lead.getCount(), lead.getId());
        Lead lead2 = new Lead("Juan Lopez", "666555444", "pipo@popi.pop", "qué calor");
        assertEquals(lead2.getCount(), lead2.getId());
        Lead lead3 = new Lead("Paco Diaz", "633222111", "paco@paco.com", "no me entero");
        assertEquals(lead3.getCount(), lead3.getId());
    }

    @Test
    void setEmail_ShouldThrowExceptionWhenFormatNotValid() {
        assertThrows(IllegalArgumentException.class, ()-> lead.setEmail("@"), "Exception should be thrown when email format is not valid");
        assertThrows(IllegalArgumentException.class, ()-> lead.setEmail("pep@@@@@@"), "Exception should be thrown when email format is not valid");
        assertThrows(IllegalArgumentException.class, ()-> lead.setEmail("pep@gog."), "Exception should be thrown when email format is not valid");
    }

    @Test
    void setEmail_ShouldSetEmailWhenPassedValidInput() {
        assertEquals("pepitopulgarcito@jotmeil.com", lead.getEmail());
        lead.setEmail("pepeperez@gugel.com");
        assertEquals("pepeperez@gugel.com", lead.getEmail());
    }

    @Test
    void setEmail_ShouldReturnTrueWhenSetEmail() {
        assertTrue(lead.setEmail("pepeperez@gugel.com"));
        assertTrue(lead.setEmail("juancho@hotmail.io"));
    }

    @Test
    void setPhoneNumber_ShouldThrowExceptionWhenFormatNotValid() {
        assertThrows(IllegalArgumentException.class, ()-> lead.setPhoneNumber("+++335544(werwer)987987"), "Exception should be thrown when phone number format is not valid");
    }

    @Test
    void setPhoneNumber_ShouldReturnTrueWhenSetPhoneNumber() {
        assertTrue(lead.setPhoneNumber("123456787"));
        assertTrue(lead.setPhoneNumber("876000123"));
        assertTrue(lead.setPhoneNumber("696858747"));
    }

    @Test
    void setPhoneNumber_ShouldSetPhoneNumberWhenPassedValidInput() {
        assertEquals("876543210", lead.getPhoneNumber());
        lead.setPhoneNumber("636525414");
        assertEquals("636525414", lead.getPhoneNumber());
    }
}
