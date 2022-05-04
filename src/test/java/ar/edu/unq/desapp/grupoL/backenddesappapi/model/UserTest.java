package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void testUserModel() {
        User user = new User("Pepe", "Garcia", "pepe@gmail.com",
                "Cordoba 5300", "pepegarcia123", "0848843953963586953104", "20532062");

        Assertions.assertEquals("Pepe", user.getName());
        Assertions.assertEquals("Garcia", user.getSurname());
        Assertions.assertEquals("pepe@gmail.com", user.getEmail());
        Assertions.assertEquals("Cordoba 5300", user.getAddress());
        Assertions.assertEquals("pepegarcia123", user.getPassword());
        Assertions.assertEquals("0848843953963586953104", user.getCvu());
        Assertions.assertEquals("20532062", user.getWallet());
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
        user.setScore("3");
        user.setOperationsNumber(10);

        Assertions.assertEquals("Pepe", user.getName());
        Assertions.assertEquals("Garcia", user.getSurname());
        Assertions.assertEquals("pepe@gmail.com", user.getEmail());
        Assertions.assertEquals("Cordoba 5300", user.getAddress());
        Assertions.assertEquals("pepegarcia123", user.getPassword());
        Assertions.assertEquals("0848843953963586953104", user.getCvu());
        Assertions.assertEquals("20532062", user.getWallet());
        Assertions.assertEquals("3", user.getScore());
        Assertions.assertEquals(10, user.getOperationsNumber());
    }
}

