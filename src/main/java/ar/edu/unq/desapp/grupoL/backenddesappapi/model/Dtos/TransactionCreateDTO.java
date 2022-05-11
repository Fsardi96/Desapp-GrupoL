package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;


public class TransactionCreateDTO {

    private String crypto;
    private Float amountOfCrypto;
    private Float priceOfCrypto;
    private String transactionType;

    public TransactionCreateDTO() {
        //Empty constructor
    }

    public TransactionCreateDTO(String crypto, Float amountOfCrypto, Float priceOfCrypto, String transactionType) {
        this.crypto = crypto;
        this.amountOfCrypto = amountOfCrypto;
        this.priceOfCrypto = priceOfCrypto;
        this.transactionType = transactionType;
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

    public Float getPriceOfCrypto() {
        return priceOfCrypto;
    }

    public void setPriceOfCrypto(Float priceOfCrypto) {
        this.priceOfCrypto = priceOfCrypto;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
