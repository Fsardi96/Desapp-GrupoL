package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CryptoCurrencyTest {

    @Test
    void testCryptoCurrencyModel() {
        CryptoCurrency crypto = new CryptoCurrency("symbol", 10f,30f, 2F, LocalDateTime.now());

        Assertions.assertEquals("symbol", crypto.getSymbol());
        Assertions.assertEquals(10f, crypto.getPrice());
       // Assertions.assertEquals("date", crypto.getLastUpdateDateAndTime());
    }

    @Test
    void testEmptyConstructor() {
        CryptoCurrency crypto = new CryptoCurrency();
        crypto.setSymbol("symbol");
        crypto.setPrice(10f);
/*        crypto.setLastUpdateDateAndTime("date");

        Assertions.assertEquals("symbol", crypto.getSymbol());
        Assertions.assertEquals(10f, crypto.getPrice());
        Assertions.assertEquals("date", crypto.getLastUpdateDateAndTime());*/
    }
}

class CryptoCurrencyListTest {
    @Test
    void testCryptoCurrencyListModel() {
        CryptoCurrencyList cryptoList = new CryptoCurrencyList();
        CryptoCurrency crypto = new CryptoCurrency();
        cryptoList.addCrypto(crypto);

        Assertions.assertTrue(cryptoList.cryptos.contains(crypto));
    }
}