package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuthUserDTOTest {

    @Test
    void testAuthUserDTOModel() {
        AuthUserDTO dto = new AuthUserDTO("name", "password");
        dto.setPassword("password");
        dto.setName("name");

        Assertions.assertEquals("name", dto.getName());
        Assertions.assertEquals("password", dto.getPassword());
    }
}