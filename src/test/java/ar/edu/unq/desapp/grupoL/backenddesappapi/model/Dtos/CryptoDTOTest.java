package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class CryptoDTOTest {

    @Test
    void testCryptoDTOModel() {
        CryptoDTO dto = new CryptoDTO("symbol", 1f, LocalDateTime.now().toString());
        String date = LocalDateTime.now().toString();
        dto.setSymbol("aSymbol");
        dto.setPrice(2f);
        dto.setLastUpdateDateAndTime(date);

        Assertions.assertEquals("aSymbol", dto.getSymbol());
        Assertions.assertEquals(2f, dto.getPrice());
        Assertions.assertEquals(date, dto.getLastUpdateDateAndTime());
    }
}
