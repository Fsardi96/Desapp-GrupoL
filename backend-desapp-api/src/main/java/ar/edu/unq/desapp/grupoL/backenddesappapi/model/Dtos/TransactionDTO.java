package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TransactionDTO {
 @Id
 private Long id;
 private String crypto;
 private Float amountOfCrypto;
 private Float priceOfCrypto;
 private Float priceInARS;
 private String transactionType;
 private String userFullName;

 public TransactionDTO(String crypto, Float amountOfCrypto, Float priceOfCrypto, Float priceInARS, String transactionType, String userFullName) {
  this.crypto = crypto;
  this.amountOfCrypto = amountOfCrypto;
  this.priceOfCrypto = priceOfCrypto;
  this.priceInARS = priceInARS;
  this.transactionType = transactionType;
  this.userFullName = userFullName;
 }

 public TransactionDTO() { }

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
}
