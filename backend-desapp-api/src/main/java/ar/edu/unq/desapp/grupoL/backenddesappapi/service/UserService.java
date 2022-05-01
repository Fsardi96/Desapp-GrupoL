package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserError;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserNotFound;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserService(){}

    private Long idgenerador = Long.valueOf(0);

    @Transactional
    public ArrayList<User> getUsers(){
        return (ArrayList<User>) this.userRepository.findAll();
    }

    @Transactional
    public User findUser(Long id){
        return  userRepository.findById(id).orElseThrow(UserNotFound::new);
    }


    @Transactional
    public User createUser(User usuario) throws UserError {
        if(this.isValidUser(usuario)){
            User user = new User(usuario.getName(), usuario.getSurname(), usuario.getEmail(), usuario.getAddress(),
                                 usuario.getPassword(), usuario.getCvu(), usuario.getWallet());
            user.setId(this.idgenerador+1);
            idgenerador++;
            return this.userRepository.save(user);
        }
        throw new UserError("One or more fields are incorrect");
    }

    public boolean isValidUser(User user) {
        return validate(user.getName(), "^[a-zA-Z]{3,30}$") &&
                validate(user.getSurname(), "^[a-zA-Z]{3,30}$") &&
                validate(user.getEmail(), "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.]+(?:\\.[a-zA-Z0-9-]+)*$") &&
                validate(user.getAddress(), "^[a-zA-Z0-9]{10,30}$") &&  //corregir que admita espacios
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

    public String getNewDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return  formatter.format(date);
    }

}
