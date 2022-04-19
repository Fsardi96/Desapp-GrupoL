package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

public class Transaction {
    private String dateAndTime;
    private Cryptocurrency crypto;
    private Float amountOfCrypto;
    private Float priceInARS;
    private User user;
    private String transactionType;

    public Transaction() { }

    public Transaction(String dateAndTime, Cryptocurrency crypto, Float amountOfCrypto, Float priceInARS, User user, String transactionType) {
        this.dateAndTime = dateAndTime;
        this.crypto = crypto;
        this.amountOfCrypto = amountOfCrypto;
        this.priceInARS = priceInARS;
        this.user = user;
        this.transactionType = transactionType;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Cryptocurrency getCrypto() {
        return crypto;
    }

    public void setCrypto(Cryptocurrency crypto) {
        this.crypto = crypto;
    }

    public Float getAmountOfCrypto() {
        return amountOfCrypto;
    }

    public void setAmountOfCrypto(Float amountOfCrypto) {
        this.amountOfCrypto = amountOfCrypto;
    }

    public Float getPriceInARS() {
        return priceInARS;
    }

    public void setPriceInARS(Float priceInARS) {
        this.priceInARS = priceInARS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
