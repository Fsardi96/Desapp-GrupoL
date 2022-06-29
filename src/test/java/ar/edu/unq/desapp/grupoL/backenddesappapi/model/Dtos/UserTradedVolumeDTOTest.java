package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UserTradedVolumeDTOTest {

    @Test
    void testUserTradedVolumeDTOModel() {
        ArrayList<CryptoActiveDTO> actives = new ArrayList<CryptoActiveDTO>();
        UserTradedVolumeDTO dto = new UserTradedVolumeDTO("userFullName", "dateAndTime",
                                                            2f, 2f, actives);

        dto.setUserFullName("aName");
        dto.setDateAndTime("date");
        dto.setVolumeInUSD(3f);
        dto.setVolumeInARS(3f);
        dto.setCryptoActives(actives);

        Assertions.assertEquals("aName", dto.getUserFullName());
        Assertions.assertEquals("date", dto.getDateAndTime());
        Assertions.assertEquals(3f, dto.getVolumeInUSD());
        Assertions.assertEquals(3f, dto.getVolumeInARS());
        Assertions.assertEquals(actives, dto.getCryptoActives());
    }
}
