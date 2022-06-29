package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Id;

public class UserDTO {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String surname;
    @JsonProperty
    private String password;


public UserDTO(@JsonProperty("id")Long id , @JsonProperty("name")String name,@JsonProperty("surname") String surname,@JsonProperty("password") String password){
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.password = password;
}

    public UserDTO() {
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
