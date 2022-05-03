package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserDTOTest {

    @Test
    void testUserDTOModel() {
        UserDTO dto = new UserDTO(1L,"name","surname");

        Assertions.assertEquals(dto.getId(), 1L);
        Assertions.assertEquals(dto.getName(), "name");
        Assertions.assertEquals(dto.getSurname(), "surname");
    }

    @Test
    void testEmptyConstructor() {
        UserDTO dto = new UserDTO();
        dto.setId(1L);
        dto.setName("name");
        dto.setSurname("surname");

        Assertions.assertEquals(dto.getId(), 1L);
        Assertions.assertEquals(dto.getName(), "name");
        Assertions.assertEquals(dto.getSurname(), "surname");
    }

}