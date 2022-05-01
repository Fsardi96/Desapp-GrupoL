package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserDTO;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Transaction {
    @Id
    private Long id;
    private String dateAndTime;

    //private CryptoCurrency crypto;
    private String crypto;
    private Float amountOfCrypto;
    private Float priceInARS;
    @OneToOne//(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserDTO user;
    private String transactionType;

    public Transaction() { }

    public Transaction(String dateAndTime, String crypto, Float amountOfCrypto, Float priceInARS, UserDTO user, String transactionType) {
        this.dateAndTime = dateAndTime;
        this.crypto = crypto;
        this.amountOfCrypto = amountOfCrypto;
        this.priceInARS = priceInARS;
        this.user = user;
        this.transactionType = transactionType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }


}
