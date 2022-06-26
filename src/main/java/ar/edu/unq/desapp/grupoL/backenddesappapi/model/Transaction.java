package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import ar.edu.unq.desapp.grupoL.backenddesappapi.serialize.TransactionJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonSerialize(using = TransactionJsonSerializer.class)
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;
    private LocalDateTime dateAndTime;
    private String crypto;
    private Float amountOfCrypto;
    private Float priceOfCrypto;
    private Float finalPriceInARS;
    @JoinColumn(name="User")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
    private String transactionType;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User secondaryUser;

    public Transaction(String crypto, User user, String transactionType, Float amountOfCrypto, Float priceOfCrypto, Float finalPriceInARS) {
        this.dateAndTime = LocalDateTime.now();
        this.crypto = crypto;
        this.user = user;
        this.transactionType = transactionType;
        this.amountOfCrypto = amountOfCrypto;
        this.priceOfCrypto = priceOfCrypto;
        this.finalPriceInARS = finalPriceInARS;
        this.status = "En curso";
        this.secondaryUser = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
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

    public Float getFinalPriceInARS() {
        return finalPriceInARS;
    }

    public void setFinalPriceInARS(Float priceInARS) {
        this.finalPriceInARS = priceInARS;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getSecondaryUser() { return secondaryUser; }

    public void setSecondaryUser(User secondaryUser) {
        this.secondaryUser = secondaryUser;
    }

    public void process(User secondaryUser) {
        user.setOperationsNumber(user.getOperationsNumber() + 1);
        secondaryUser.setOperationsNumber(secondaryUser.getOperationsNumber() + 1);

        long difference = ChronoUnit.MINUTES.between(LocalDateTime.now(), this.getDateAndTime());

        if(difference <= 30){
            defineScore(this.getUser(),10);
            defineScore(secondaryUser,10);
        }else {
            defineScore(this.getUser(),5);
            defineScore(secondaryUser,5);
        }
        this.changeStatusToProcessed();
    }

    public void defineScore(User user, int scoreToIncrement){
        if(user.getScore().equals("Sin operaciones")){
            user.setScore(String.valueOf(scoreToIncrement));
        }else{
            Integer newScore = Integer.parseInt(user.getScore()) + scoreToIncrement;
            user.setScore(newScore.toString());
        }
    }


    public void cancel() {
        if (user.getScore().equals("Sin operaciones")) {
            user.setScore("0");
        } else {
            Integer newScore = (Integer.parseInt(user.getScore()) - 20);
            user.setScore(newScore.toString());
        }
        this.changeStatusToCancelled();
    }

    public String getAddress() {
        if (transactionType.equals("COMPRA")) {
            return user.getWallet();
        } else {
            return user.getCvu();
        }
    }

    public void changeStatusToProcessed() {
        this.setStatus("Procesada");
    }

    public void changeStatusToCancelled() {
        this.setStatus("Cancelada");
    }
}
