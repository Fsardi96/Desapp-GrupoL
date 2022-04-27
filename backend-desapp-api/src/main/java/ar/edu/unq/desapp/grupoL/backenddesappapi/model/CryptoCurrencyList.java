package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import java.util.ArrayList;

public class CryptoCurrencyList {
    public ArrayList<CryptoCurrency> cryptos = new ArrayList<CryptoCurrency>();

    public CryptoCurrencyList() { }

    public void addCrypto(CryptoCurrency crypto) {
        cryptos.add(crypto);
    }
}
