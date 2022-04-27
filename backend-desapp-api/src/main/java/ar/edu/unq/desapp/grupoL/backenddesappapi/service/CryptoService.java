package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.webservice.UserController;

public class CryptoService {

    private UserController controller;

    public CryptoCurrency getCryptoCurrencyValue(String symbol) {
        CryptoCurrency cryptoCurrency = new CryptoCurrency();

        return cryptoCurrency;
    }
}
