package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
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
    private String algo;
    @OneToMany
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public User() { }

    public User(String name, String surname, String email, String address, String password, String cvu, String wallet, String algo) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.password = password;
        this.cvu = cvu;
        this.wallet = wallet;
        this.transactions = new ArrayList<>();
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

    /*public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }*/
    public void addTransaction (Transaction transaction){
        this.transactions.add(transaction);
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

}

