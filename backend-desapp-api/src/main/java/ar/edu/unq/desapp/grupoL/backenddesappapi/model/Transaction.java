package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
    @Id
    private Long id = Long.valueOf(0);
    private String dateAndTime;

    //private CryptoCurrency crypto;
    private String crypto;
    private Float amountOfCrypto;
    private Float priceInARS;
    @ManyToOne
    private User user;
    private String transactionType;

    public Transaction() { }

    public Transaction(String dateAndTime, String crypto, Float amountOfCrypto, Float priceInARS, User user, String transactionType) {
        this.dateAndTime = dateAndTime;
        this.crypto = crypto;
        this.amountOfCrypto = amountOfCrypto;
        this.priceInARS = priceInARS;
        this.user = user;
        this.transactionType = transactionType;
    }

    /*public Long incrementarID(){
        return this.id ++;
    }*/

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getCrypto() {
        return crypto;
    }

    public void setCrypto(String crypto) {
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
