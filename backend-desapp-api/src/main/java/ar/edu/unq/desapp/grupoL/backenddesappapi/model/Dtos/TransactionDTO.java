package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;


import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionDTO {


 @JsonProperty
    private String crypto;
 @JsonProperty
    private Float amountOfCrypto;
 @JsonProperty
    private Float priceOfCryto;
 @JsonProperty
    private String transactionType;




public TransactionDTO( @JsonProperty("crypto") String crypto, @JsonProperty("amountOfCrypto") Float amountOfCrypto, @JsonProperty("priceOfCryto")Float priceOfCryto,
                       @JsonProperty("transactionType")String transactionType){

 this.crypto = crypto;
 this.amountOfCrypto = amountOfCrypto;
 this.priceOfCryto = priceOfCryto;
 this.transactionType =  transactionType;
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

 public Float getPriceOfCryto() {
  return priceOfCryto;
 }

 public void setPriceOfCryto(Float priceOfCryto) {
  this.priceOfCryto = priceOfCryto;
 }

 public String getTransactionType() {
  return transactionType;
 }

 public void setTransactionType(String transactionType) {
  this.transactionType = transactionType;
 }
}
