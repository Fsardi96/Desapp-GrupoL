package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@JsonSerialize(using = TransactionJsonSerializer.class)
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String dateAndTime;
    //private CryptoCurrency crypto;
    private String crypto;
    private Float amountOfCrypto;
    private Float priceOfCrypto;
    private Float priceInARS;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
    private String transactionType;

    public Transaction() { }

    public Transaction(String dateAndTime, String crypto, Float amountOfCrypto, Float priceOfCrypto, User user, String transactionType) {
        this.dateAndTime = dateAndTime;
        this.crypto = crypto;
        this.amountOfCrypto = amountOfCrypto;
        this.priceOfCrypto = priceOfCrypto;
        this.priceInARS = amountOfCrypto* priceOfCrypto; //calcular en base a la cot del dolar
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


    public Float getPriceOfCrypto() {
        return priceOfCrypto;
    }

    public void setPriceOfCrypto(Float priceOfCryto) {
        this.priceOfCrypto = priceOfCryto;
    }
}
