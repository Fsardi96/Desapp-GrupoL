package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

public class TransactionDTO {

   // private Long id;
    private String dateAndTime;
    private String crypto;
    private Float amountOfCrypto;
    private Float priceOfCryto;
    private Float priceInARS;
    //@OneToOne//(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserDTO user;
    private Integer operationsNumber;
    private String score;



public TransactionDTO(String dateAndTime, String crypto, Float amountOfCrypto, Float priceOfCryto, Float priceInARS, UserDTO user, Integer operationsNumber, String score){
 this.dateAndTime = dateAndTime;
 this.crypto = crypto;
 this.amountOfCrypto = amountOfCrypto;
 this.priceOfCryto = priceOfCryto;
 this.priceInARS = priceInARS;
 this.user = user;
 this.operationsNumber = operationsNumber;
 this.score = score;
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

 public Float getPriceOfCryto() {
  return priceOfCryto;
 }

 public void setPriceOfCryto(Float priceOfCryto) {
  this.priceOfCryto = priceOfCryto;
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
}
