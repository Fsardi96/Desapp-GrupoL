package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserErrorTest {
    @Test
    void testUserErrorModel () {
        UserError error = new UserError("error");
        Assertions.assertEquals(error.getMessage(), "error");
    }
}