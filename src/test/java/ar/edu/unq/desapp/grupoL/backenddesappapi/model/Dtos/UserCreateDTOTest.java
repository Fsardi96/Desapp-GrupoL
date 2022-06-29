package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserCreateDTOTest {

    @Test
    void testUserCreateDTOModel() {
        UserCreateDTO dto = new UserCreateDTO("name","surname","email", "address",
                                            "password", "cvu", "wallet");
        dto.setName("aName");
        dto.setSurname("aSurname");
        dto.setEmail("anEmail");
        dto.setAddress("anAddress");
        dto.setPassword("aPassword");
        dto.setCvu("aCvu");
        dto.setWallet("aWallet");

        Assertions.assertEquals("aName", dto.getName());
        Assertions.assertEquals("aSurname", dto.getSurname());
        Assertions.assertEquals("anEmail", dto.getEmail());
        Assertions.assertEquals("anAddress", dto.getAddress());
        Assertions.assertEquals("aPassword", dto.getPassword());
        Assertions.assertEquals("aCvu", dto.getCvu());
        Assertions.assertEquals("aWallet", dto.getWallet());
    }
}
