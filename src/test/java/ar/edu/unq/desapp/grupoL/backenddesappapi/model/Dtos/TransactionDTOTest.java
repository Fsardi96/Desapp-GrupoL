package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionDTOTest {
    @Test
    void testTransactionDTOModel() {
        TransactionDTO dto = new TransactionDTO(2L, "dateAndTime", "crypto",10f,10f,
                10f,"type","Pepe", 0, "SinOperaciones");

        Assertions.assertEquals(dto.getId(), 2L);
        Assertions.assertEquals(dto.getCrypto(), "crypto");
        Assertions.assertEquals(dto.getAmountOfCrypto(), 10f);
        Assertions.assertEquals(dto.getPriceOfCrypto(), 10f);
        Assertions.assertEquals(dto.getPriceInARS(), 10f);
        Assertions.assertEquals(dto.getTransactionType(), "type");
        Assertions.assertEquals(dto.getUserFullName(), "Pepe");
        Assertions.assertEquals(dto.getOperationsNumber(), 0);
        Assertions.assertEquals(dto.getScore(), "SinOperaciones");
    }

    @Test
    void testEmptyConstructor() {
        TransactionDTO dto = new TransactionDTO();
        dto.setTransactionType("type");
        dto.setCrypto("crypto");
        dto.setId(2L);
        dto.setAmountOfCrypto(10f);
        dto.setPriceInARS(10f);
        dto.setPriceOfCrypto(10f);
        dto.setUserFullName("Pepe");

        Assertions.assertEquals(dto.getId(), 2L);
        Assertions.assertEquals(dto.getCrypto(), "crypto");
        Assertions.assertEquals(dto.getAmountOfCrypto(), 10f);
        Assertions.assertEquals(dto.getPriceOfCrypto(), 10f);
        Assertions.assertEquals(dto.getPriceInARS(), 10f);
        Assertions.assertEquals(dto.getTransactionType(), "type");
        Assertions.assertEquals(dto.getUserFullName(), "Pepe");
    }
}