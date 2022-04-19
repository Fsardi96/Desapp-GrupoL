package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testConstructor() {
        User user = new User("Pepe", "Garcia", "pepe@gmail.com",
                "Cordoba 5300", "pepegarcia123", "0848843953963586953104", "20532062");

        Assertions.assertEquals(user.getName(), "Pepe");
        Assertions.assertEquals(user.getSurname(), "Garcia");
        Assertions.assertEquals(user.getEmail(), "pepe@gmail.com");
        Assertions.assertEquals(user.getAddress(), "Cordoba 5300");
        Assertions.assertEquals(user.getPassword(), "pepegarcia123");
        Assertions.assertEquals(user.getCvu(), "0848843953963586953104");
        Assertions.assertEquals(user.getWallet(), "20532062");
    }

    @Test
    void testEmptyConstructor() {
        User user = new User();
        user.setName("Pepe");
        user.setSurname("Garcia");
        user.setEmail("pepe@gmail.com");
        user.setAddress("Cordoba 5300");
        user.setPassword("pepegarcia123");
        user.setCvu("0848843953963586953104");
        user.setWallet("20532062");

        Assertions.assertEquals(user.getName(), "Pepe");
        Assertions.assertEquals(user.getSurname(), "Garcia");
        Assertions.assertEquals(user.getEmail(), "pepe@gmail.com");
        Assertions.assertEquals(user.getAddress(), "Cordoba 5300");
        Assertions.assertEquals(user.getPassword(), "pepegarcia123");
        Assertions.assertEquals(user.getCvu(), "0848843953963586953104");
        Assertions.assertEquals(user.getWallet(), "20532062");
    }
}