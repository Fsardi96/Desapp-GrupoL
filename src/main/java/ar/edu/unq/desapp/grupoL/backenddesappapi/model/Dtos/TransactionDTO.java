package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import javax.persistence.Id;
import java.time.LocalDateTime;

public class TransactionDTO {
 private Long id;
 private String dateAndTime;
 private String crypto;
 private Float amountOfCrypto;
 private Float priceOfCrypto;
 private Float priceInARS;
 private String transactionType;
 private String userFullName;
 private Integer operationsNumber;
 private String score;
 private String status;

 public TransactionDTO(Long id, String dateAndTime, String crypto, Float amountOfCrypto, Float priceOfCrypto, Float priceInARS,
                       String transactionType, String userFullName, Integer operationsNumber, String score, String status) {
  this.id = id;
  this.dateAndTime = dateAndTime;
  this.crypto = crypto;
  this.amountOfCrypto = amountOfCrypto;
  this.priceOfCrypto = priceOfCrypto;
  this.priceInARS = priceInARS;
  this.transactionType = transactionType;
  this.userFullName = userFullName;
  this.operationsNumber = operationsNumber;
  this.score = score;
  this.status = status;
 }

 public TransactionDTO() {
  //Empty constructor
 }

 @Id
 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
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

 public Float getPriceInARS() {
  return priceInARS;
 }

 public void setPriceInARS(Float priceInARS) {
  this.priceInARS = priceInARS;
 }

 public String getTransactionType() {
  return transactionType;
 }

 public void setTransactionType(String transactionType) {
  this.transactionType = transactionType;
 }

 public String getUserFullName() {
  return userFullName;
 }

 public void setUserFullName(String userFullName) {
  this.userFullName = userFullName;
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

 public String getDateAndTime() {
  return dateAndTime;
 }

 public void setDateAndTime(String dateAndTime) {
  this.dateAndTime = dateAndTime;
 }

 public String getStatus() {
  return status;
 }

 public void setStatus(String status) {
  this.status = status;
 }
}
