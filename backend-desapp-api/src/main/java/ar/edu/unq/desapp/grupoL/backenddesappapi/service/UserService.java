package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public UserService(){}


    @Transactional
    public ArrayList<User> getUsers(){
        return (ArrayList<User>) this.userRepository.findAll();
    }

    @Transactional
    public User createUser(User usuario){
        return this.userRepository.save(usuario);
    }
}
