package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionNotFoundTest {
    @Test
    void testTransactionNotFoundModel () {
        TransactionNotFound error = new TransactionNotFound("error");
        Assertions.assertEquals(error.getMessage(), "error");
    }
}