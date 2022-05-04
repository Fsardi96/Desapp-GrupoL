package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class CryptoCurrency {
    @Id
    private String symbol;
    private Float price;
    private String lastUpdateDateAndTime;

    public CryptoCurrency() { }

    public CryptoCurrency(String symbol, Float price, String lastUpdateDateAndTime) {
        this.symbol = symbol;
        this.price = price;
        this.lastUpdateDateAndTime = lastUpdateDateAndTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getLastUpdateDateAndTime() {
        return lastUpdateDateAndTime;
    }

    public void setLastUpdateDateAndTime(String lastUpdateDateAndTime) {
        this.lastUpdateDateAndTime = lastUpdateDateAndTime;
    }
}
