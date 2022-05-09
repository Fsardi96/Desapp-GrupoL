package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;


public class UserDTO {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String surname;


public UserDTO(@JsonProperty("id")Long id , @JsonProperty("name")String name,@JsonProperty("surname") String surname){
    this.id = id;
    this.name = name;
    this.surname = surname;
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
}
