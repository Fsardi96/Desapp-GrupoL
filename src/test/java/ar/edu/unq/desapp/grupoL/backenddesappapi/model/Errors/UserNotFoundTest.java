package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNotFoundTest {
    @Test
    void testUserNotFoundModel () {
        UserNotFound error = new UserNotFound();
        Assertions.assertEquals(error.getMessage(), "There is not a registered user with that ID");
    }
}