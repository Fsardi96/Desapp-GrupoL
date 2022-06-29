package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionCreateDTOTest {

    @Test
    void testTransactionCreateDTOModel() {
        TransactionCreateDTO dto = new TransactionCreateDTO("symbol", 2f, "type");
        dto.setTransactionType("aType");
        dto.setCrypto("aSymbol");

        Assertions.assertEquals("aType", dto.getTransactionType());
        Assertions.assertEquals("aSymbol", dto.getCryptoSymbol());
        Assertions.assertEquals(2f, dto.getAmountOfCrypto());
    }
}
