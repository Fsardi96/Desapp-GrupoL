package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class TransactionTest {

    User user = new User("Pepe", "Garcia", "pepe@gmail.com",
            "Cordoba 5300", "pepegarcia123", "0848843953963586953104", "20532062");

    @Test
    void testTransactionModel() {

        CryptoCurrency crypto = new CryptoCurrency("symbol", 10f,30f, 2F, LocalDateTime.now());
        Transaction transaction = new Transaction("symbol", user, "transactionType",1f,1f,1f);

        //Assertions.assertEquals(LocalDateTime.now(), transaction.getDateAndTime());
        Assertions.assertEquals(2f, transaction.getAmountOfCrypto());
        Assertions.assertEquals(10f, transaction.getPriceOfCrypto());
        Assertions.assertEquals(transaction.getUser(), user);
        Assertions.assertEquals("transactionType", transaction.getTransactionType());
    }

    @Test
    void testEmptyConstructor() {
        Transaction transaction = new Transaction();
        transaction.setDateAndTime(LocalDateTime.now());
        transaction.setAmountOfCrypto(10f);
        transaction.setPriceOfCrypto(10f);
        transaction.setUser(user);
        transaction.setTransactionType("transactionType");
        transaction.setFinalPriceInARS(10f);

       // Assertions.assertEquals(LocalDateTime.now(), transaction.getDateAndTime());
        Assertions.assertEquals(10f, transaction.getAmountOfCrypto());
        Assertions.assertEquals(10f, transaction.getPriceOfCrypto());
        Assertions.assertEquals(transaction.getUser(), user);
        Assertions.assertEquals("transactionType", transaction.getTransactionType());
        Assertions.assertEquals(10f, transaction.getFinalPriceInARS());
    }
}