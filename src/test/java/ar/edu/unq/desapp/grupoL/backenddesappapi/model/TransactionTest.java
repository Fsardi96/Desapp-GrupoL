package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class TransactionTest {

    User user = new User("Pepe", "Garcia", "pepe@gmail.com",
            "Cordoba 5300", "pepegarcia123", "0848843953963586953104", "20532062");
    User sencondUser = new User("Maria", "Perez", "maria@gmail.com",
            "Cordoba 5300", "mariaperez123", "0848843953963586953105", "20532065");


    @Test
    void testTransactionModel() {
        Transaction transaction = new Transaction("symbol", user, "COMPRA",1f,1f,1f);

        Assertions.assertEquals(1f, transaction.getAmountOfCrypto());
        Assertions.assertEquals(1f, transaction.getPriceOfCrypto());
        Assertions.assertEquals(transaction.getUser(), user);
        Assertions.assertEquals("COMPRA", transaction.getTransactionType());
        Assertions.assertEquals("20532062", transaction.getAddress());

        transaction.process(sencondUser);
        Assertions.assertEquals("10", transaction.getUser().getScore());
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

        Assertions.assertEquals(10f, transaction.getAmountOfCrypto());
        Assertions.assertEquals(10f, transaction.getPriceOfCrypto());
        Assertions.assertEquals(transaction.getUser(), user);
        Assertions.assertEquals("transactionType", transaction.getTransactionType());
        Assertions.assertEquals(10f, transaction.getFinalPriceInARS());
        transaction.cancel();
        Assertions.assertEquals("0", transaction.getUser().getScore());
    }
}
