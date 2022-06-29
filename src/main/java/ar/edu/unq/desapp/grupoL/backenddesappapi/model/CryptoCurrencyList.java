package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.BaseCryptoDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CryptoCurrencyList {
    public String date;
    public ArrayList<BaseCryptoDTO> cryptos = new ArrayList<>();

    public CryptoCurrencyList() {
        this.date = LocalDateTime.now().toString();
    }

    public void addCrypto(BaseCryptoDTO crypto) {
        cryptos.add(crypto);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<BaseCryptoDTO> getCryptos() {
        return cryptos;
    }

    public void setCryptos(ArrayList<BaseCryptoDTO> cryptos) {
        this.cryptos = cryptos;
    }
}
