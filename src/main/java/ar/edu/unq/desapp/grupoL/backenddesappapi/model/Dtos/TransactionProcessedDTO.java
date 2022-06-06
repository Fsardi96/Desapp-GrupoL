package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

public class TransactionProcessedDTO {

    private String cryptoSymbol;
    private Float amountOfCrypto;
    private Float priceOfCrypto;
    private Float finalPriceInARS;
    private String userName;
    private Integer operationsNumber;
    private String score;
    private String address;

    public TransactionProcessedDTO(String cryptoSymbol, Float amountOfCrypto, Float priceOfCrypto,
                                   Float finalPriceInARS, String userName, Integer operationsNumber,
                                   String score, String address) {
        this.cryptoSymbol = cryptoSymbol;
        this.amountOfCrypto = amountOfCrypto;
        this.priceOfCrypto = priceOfCrypto;
        this.finalPriceInARS = finalPriceInARS;
        this.userName = userName;
        this.operationsNumber = operationsNumber;
        this.score = score;
        this.address = address;
    }

    public TransactionProcessedDTO() { }

    public String getCryptoSymbol() {
        return cryptoSymbol;
    }

    public void setCryptoSymbol(String cryptoSymbol) {
        this.cryptoSymbol = cryptoSymbol;
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

    public Float getFinalPriceInARS() {
        return finalPriceInARS;
    }

    public void setFinalPriceInARS(Float finalPriceInARS) {
        this.finalPriceInARS = finalPriceInARS;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getOperationsNumber() {
        return operationsNumber;
    }

    public void setOperationsNumber(Integer operationsNumber) {
        this.operationsNumber = operationsNumber;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
