package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class UserDTO {
    @Id
    private Long id;
    private String name;
    private String surname;


public UserDTO(Long id , String name, String surname){
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
