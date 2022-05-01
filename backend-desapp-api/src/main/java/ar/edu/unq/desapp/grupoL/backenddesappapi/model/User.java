package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import ar.edu.unq.desapp.grupoL.backenddesappapi.serialize.UserJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
//@JsonSerialize(using = UserJsonSerializer.class)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String password;
    private String cvu;
    private String wallet;
    private String score;
    private Integer operationsNumber;
    /*@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //LAZY O EAGER?
    private ArrayList<Transaction> transactions = new ArrayList<>();*/

    public User() { }

    public User(String name, String surname, String email, String address, String password, String cvu, String wallet) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.password = password;
        this.cvu = cvu;
        this.wallet = wallet;
        this.score = "Sin operaciones";
        this.operationsNumber = 0;
        //this.transactions = new ArrayList<>();
    }

   /* public Long incrementarID(){
        this.id ++;
        return this.id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
/*
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }


    public void addTransaction (Transaction transaction){
        this.transactions.add(transaction);
    }*/

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getOperationsNumber() {
        return operationsNumber;
    }

    public void setOperationsNumber(Integer operationsNumber) {
        this.operationsNumber = operationsNumber;
    }
}

