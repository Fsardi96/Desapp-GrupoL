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
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CryptoCurrency crypto;
    private Float amountOfCrypto;
    private Float priceOfCrypto;
    private Float finalPriceInARS;
    @JoinColumn(name="User")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
    private String transactionType;

    public Transaction( CryptoCurrency crypto, User user, String transactionType) {
        this.dateAndTime = LocalDateTime.now();
        this.crypto = crypto;
        this.user = user;
        this.transactionType = transactionType;
        this.amountOfCrypto = crypto.getAmount();
        this.priceOfCrypto = crypto.getPrice();
        this.finalPriceInARS = crypto.getPriceInARS() * this.amountOfCrypto;
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

    public CryptoCurrency getCrypto() {
        return crypto;
    }

    public void setCrypto(CryptoCurrency crypto) {
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

    public void process() {
        user.setOperationsNumber(user.getOperationsNumber() + 1);

    }

    public void cancel() {
        if (user.getScore().equals("Sin operaciones")) {
            user.setScore("0");
        } else {
            Integer newScore = (Integer.parseInt(user.getScore()) - 20);
            user.setScore(newScore.toString());
        }
    }

    public String getAddress() {
        if (transactionType.equals("COMPRA")) {
            return user.getWallet();
        } else {
            return user.getCvu();
        }
    }
}
