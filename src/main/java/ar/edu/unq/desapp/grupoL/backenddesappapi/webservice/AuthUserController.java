package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;


import ar.edu.unq.desapp.grupoL.backenddesappapi.aspects.LogExecutionTime;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.AuthUserDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TokenDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthUserController {

    @Autowired
    UserService userService;

/*

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthUserDTO authUserDto){
        TokenDTO tokenDto = userService.login(authUserDto);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }


    @PostMapping("/validate")
    public ResponseEntity<TokenDTO> validate(@RequestParam String token){
        TokenDTO tokenDto = userService.validate(token);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }
*/


}