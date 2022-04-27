package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserError;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Optional<User> findUser(Long id){
        return this.userRepository.findById(id);
    }

    @Transactional
    public User createUser(User usuario) throws UserError {
        if(this.isValidUser(usuario)){
            return this.userRepository.save(usuario);
        }
        throw new UserError("One or more fields are incorrect");
    }

    public boolean isValidUser(User user) {
        return validate(user.getName(), "^[a-zA-Z]{3,30}$") &&
                validate(user.getSurname(), "^[a-zA-Z]{3,30}$") &&
                validate(user.getEmail(), "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.]+(?:\\.[a-zA-Z0-9-]+)*$") &&
                validate(user.getAddress(), "^[a-zA-Z0-9]{10,30}$") &&
                validate(user.getPassword(), "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{6,}$") &&
                validate(user.getCvu(), "^[a-zA-Z0-9]{22}$") &&
                validate(user.getWallet(), "^[a-zA-Z0-9]{8}$");
    }

    public boolean validate(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    @Transactional
    public void deleteUser(Long id){
        this.userRepository.deleteById(id);
    }

}
