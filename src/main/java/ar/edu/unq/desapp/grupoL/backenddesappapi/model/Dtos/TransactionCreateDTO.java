package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

public class TransactionCreateDTO {

    private String cryptoSymbol;
    private Float amountOfCrypto;
    private String transactionType;

    public TransactionCreateDTO() {
        //Empty constructor
    }

    public TransactionCreateDTO(String cryptoSymbol, Float amountOfCrypto, String transactionType) {
        this.cryptoSymbol = cryptoSymbol;
        this.amountOfCrypto = amountOfCrypto;
        this.transactionType = transactionType;
    }

    public String getCryptoSymbol() {
        return cryptoSymbol;
    }

    public void setCrypto(String cryptoSymbol) {
        this.cryptoSymbol = cryptoSymbol;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Float getAmountOfCrypto() { return amountOfCrypto; }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
