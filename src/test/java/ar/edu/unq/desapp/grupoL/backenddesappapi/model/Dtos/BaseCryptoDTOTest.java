package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BaseCryptoDTOTest {

    @Test
    void testBaseCryptoDTOModel() {
        BaseCryptoDTO dto = new BaseCryptoDTO("symbol", 1f);
        dto.setPrice(2f);
        dto.setSymbol("aSymbol");

        Assertions.assertEquals("aSymbol", dto.getSymbol());
        Assertions.assertEquals(2f, dto.getPrice());
    }
}
