package ar.edu.unq.desapp.grupoL.backenddesappapi.service;


import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.InvalidTokenException;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws InvalidTokenException {
        Optional<User> foundUser = userRepo.findUserByName(name);
        if(!foundUser.isPresent())
            throw new InvalidTokenException("Invalid token");
        User user = foundUser.get();
        return new org.springframework.security.core.userdetails.User(
                name,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity handleException(InvalidTokenException e) {
        String transaction_invalid_json = new JSONObject()
                .put("message","User not found").toString();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(transaction_invalid_json);
    }
}
