package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;


import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.AuthUserDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.InvalidUserOrPassword;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserAlreadyExists;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserNotFound;
import ar.edu.unq.desapp.grupoL.backenddesappapi.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

/*
@RestController
@RequestMapping("/api/auth")
public class AuthUserController {

    @Autowired private JWTUtil jwtUtil;
    @Autowired private AuthenticationManager authManager;


    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody AuthUserDTO body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getName(), body.getPassword());
            authManager.authenticate(authInputToken);
        }catch(Exception e){
            throw new InvalidUserOrPassword();
        }
        String token = jwtUtil.generateToken(body.getName());
        return Collections.singletonMap("jwt-token", token);
    }
}*/
