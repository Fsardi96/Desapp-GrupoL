package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

public class CryptoActiveDTO {
    private String symbol;
    private Float amount;
    private Float priceInUSD;
    private Float priceInARS;

    public CryptoActiveDTO(String symbol, Float amount, Float priceInUSD, Float priceInARS) {
        this.symbol = symbol;
        this.amount = amount;
        this.priceInUSD = priceInUSD;
        this.priceInARS = priceInARS;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getPriceInUSD() {
        return priceInUSD;
    }

    public void setPriceInUSD(Float priceInUSD) {
        this.priceInUSD = priceInUSD;
    }

    public Float getPriceInARS() {
        return priceInARS;
    }

    public void setPriceInARS(Float priceInARS) {
        this.priceInARS = priceInARS;
    }
}
