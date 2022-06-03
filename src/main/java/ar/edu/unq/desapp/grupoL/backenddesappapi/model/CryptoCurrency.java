package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@Builder
public class CryptoCurrency {
    @Id
    private String symbol;
    private Float price;
    private Float priceInARS;
    private Float amount;
    private String lastUpdateDateAndTime;

    public CryptoCurrency(String symbol, Float price, Float priceInARS, Float amount, String lastUpdateDateAndTime) {
        this.symbol = symbol;
        this.price = price;
        this.priceInARS = priceInARS;
        this.amount = amount;
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
