package com.ironhack.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void getId_currentCountAsId() {
        Contact contact1 = new Contact("Mónica", "656259795", "moni_ca@gmail.com", "AIre");
        assertEquals(contact1.getId(), contact1.getCount());
        Contact contact2 = new Contact("Noelia", "655687456", "noeliapd@gmail.com", "Delusion");
        assertEquals(contact2.getId(), contact2.getCount());
    }

}
