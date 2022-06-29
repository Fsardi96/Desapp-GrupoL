package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CryptoActiveDTOTest {

    @Test
    void testCryptoActiveDTOModel() {
        CryptoActiveDTO dto = new CryptoActiveDTO("symbol", 1f, 2f, 3f);
        dto.setSymbol("aSymbol");
        dto.setAmount(2f);
        dto.setPriceInUSD(3f);
        dto.setPriceInARS(4f);

        Assertions.assertEquals("aSymbol", dto.getSymbol());
        Assertions.assertEquals(2f, dto.getAmount());
        Assertions.assertEquals(3f, dto.getPriceInUSD());
        Assertions.assertEquals(4f, dto.getPriceInARS());
    }
}
