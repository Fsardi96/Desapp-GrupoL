package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.DatesDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserAlreadyExists;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserError;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserNotFound;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserService() {
        //Empty constructor
    }

    @Transactional
    public ArrayList<User> getUsers(){
        return (ArrayList<User>) this.userRepository.findAll();
    }

    @Transactional
    public User findUser(Long id){
        return  userRepository.findById(id).orElseThrow(UserNotFound::new);
    }


    @Transactional
    public User createUser(UserCreateDTO usuario) {
        if(userAlreadyExists(usuario)){
            throw new UserAlreadyExists();
        }
        if(this.isValidUser(usuario)){
            User user = new User(usuario.getName(), usuario.getSurname(), usuario.getEmail(), usuario.getAddress(),
                                 usuario.getPassword(), usuario.getCvu(), usuario.getWallet());
            return this.userRepository.save(user);
        }
        throw new UserError("One or more fields are incorrect");
    }

    public boolean isValidUser(UserCreateDTO user) {
        return  validate(user.getName(), "^[a-zA-Z]{3,30}$") &&
                validate(user.getSurname(), "^[a-zA-Z ]{3,30}$") &&
                validate(user.getEmail(), "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.]+(?:\\.[a-zA-Z0-9-]+)*$") &&
                validate(user.getAddress(), "^[a-zA-Z0-9 ]{10,30}$") &&
                validate(user.getPassword(), "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.])(?=\\S+$).{6,}$") &&
                validate(user.getCvu(), "^[a-zA-Z0-9]{22}$") &&
                validate(user.getWallet(), "^[a-zA-Z0-9]{8}$");
    }

    public boolean userAlreadyExists(UserCreateDTO user){
       return userRepository.existWallet(user.getWallet());
    }



    public boolean validate(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    @Transactional
    public void updateUser(User user) {
        this.userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id){
        User user = this.findUser(id);
        this.userRepository.deleteById(user.getId());
    }

    public void getTradedVolume(DatesDTO dates, Long id) {

        User userFound = this.findUser(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateFrom = LocalDateTime.parse(dates.getDate1(), formatter);
        LocalDateTime dateTo = LocalDateTime.parse(dates.getDate2(), formatter);



    }
}
