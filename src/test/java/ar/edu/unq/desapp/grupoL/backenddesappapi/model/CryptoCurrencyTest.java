package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CryptoCurrencyTest {

    @Test
    void testCryptoCurrencyModel() {
        CryptoCurrency crypto = new CryptoCurrency("symbol", 10f, "date");

        Assertions.assertEquals(crypto.getSymbol(), "symbol");
        Assertions.assertEquals(crypto.getPrice(), 10f);
        Assertions.assertEquals(crypto.getLastUpdateDateAndTime(), "date");
    }

    @Test
    void testEmptyConstructor() {
        CryptoCurrency crypto = new CryptoCurrency();
        crypto.setSymbol("symbol");
        crypto.setPrice(10f);
        crypto.setLastUpdateDateAndTime("date");

        Assertions.assertEquals(crypto.getSymbol(), "symbol");
        Assertions.assertEquals(crypto.getPrice(), 10f);
        Assertions.assertEquals(crypto.getLastUpdateDateAndTime(), "date");
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