package ar.edu.unq.desapp.grupoL.backenddesappapi.model;

public class User {

    private String name;
    private String lastname;
    private String email;
    private String address;
    private  int password;
    private int cvuMercadoPago;
    private int cryptoWalletAddress;
    private int score;

    //Constructor
    public User(String name, String lastname, String email, String address, int password, int cvuMercadoPago, int cryptoWalletAddress, int score)  {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.password = password;
        this.cvuMercadoPago = cvuMercadoPago;
        this.cryptoWalletAddress = cryptoWalletAddress ;
        this.score= score;
    }









}









