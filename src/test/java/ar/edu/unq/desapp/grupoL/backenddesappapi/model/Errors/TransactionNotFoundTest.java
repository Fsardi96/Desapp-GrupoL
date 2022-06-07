package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionNotFoundTest {
    @Test
    void testTransactionNotFoundModel () {
        TransactionNotFound error = new TransactionNotFound();
        Assertions.assertEquals("There is not a transaction with that ID", error.getMessage());
    }
}