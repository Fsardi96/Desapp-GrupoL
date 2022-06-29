package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionProcessedDTOTest {

    @Test
    void testTransactionProcessedDTOModel() {
        TransactionProcessedDTO dto = new TransactionProcessedDTO("symbol", 1f,
                                                                2f, 3f,
                                                                "userName", 2,
                                                                "score", "address", "status");
        dto.setCryptoSymbol("aSymbol");
        dto.setAmountOfCrypto(2f);
        dto.setPriceOfCrypto(3f);
        dto.setFinalPriceInARS(4f);
        dto.setUserName("name");
        dto.setOperationsNumber(3);
        dto.setScore("aScore");
        dto.setAddress("anAddress");
        dto.setStatus("aStatus");

        Assertions.assertEquals("aSymbol", dto.getCryptoSymbol());
        Assertions.assertEquals(2f, dto.getAmountOfCrypto());
        Assertions.assertEquals(3f, dto.getPriceOfCrypto());
        Assertions.assertEquals(4f, dto.getFinalPriceInARS());
        Assertions.assertEquals("name", dto.getUserName());
        Assertions.assertEquals("aScore", dto.getScore());
        Assertions.assertEquals("anAddress", dto.getAddress());
        Assertions.assertEquals("aStatus", dto.getStatus());
    }
}
