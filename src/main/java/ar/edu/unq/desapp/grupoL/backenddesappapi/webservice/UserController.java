package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;


import ar.edu.unq.desapp.grupoL.backenddesappapi.aspects.LogExecutionTimeAspectAnnotation;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.AuthUserDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.DatesDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserTradedVolumeDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.InvalidUserOrPassword;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserError;
import ar.edu.unq.desapp.grupoL.backenddesappapi.security.JWTUtil;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Api(tags = "User services")
@Tag(name = "User services", description = "Manage users")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;


    @PostMapping("/auth/login")
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

    @Operation(summary = "Get all users")
    @GetMapping("/users")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    @Operation(summary = "Get user")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserByID(@Parameter(description = "The user ID that needs to be fetched", required = true)
                                                @PathVariable Long id){
        User userFound = userService.findUser(id);
        return ResponseEntity.ok().body(userFound);
    }

    @Operation(summary = "Register user")
    @PostMapping(path="/registerUser" , consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@Parameter(description = "The user to be registered", required = true)
                               @RequestBody UserCreateDTO user) throws UserError{

        return ResponseEntity.ok().body(this.userService.createUser(user));
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@Parameter(description = "The user ID to be deleted", required = true)
                               @PathVariable Long id){
        List<Transaction> transactionsByID = this.transactionService.getTransactionsByUserId(id);
        if(!transactionsByID.isEmpty()) {
            transactionsByID.forEach(transaction -> this.transactionService.deleteTransaction(transaction.getId()));
        }else{
            this.userService.deleteUser(id);
        }
        return("The user was successfully deleted");
    }

    @Operation(summary = "Get user transaction between dates")
    @GetMapping("/tradedVolume/userID={id}")
    public ResponseEntity<UserTradedVolumeDTO> getTradedVolume(@Parameter(description = "The user ID that needs to be fetched", required = true)
                                                @PathVariable Long id, @RequestBody DatesDTO dates){
        List<Transaction> transactions = transactionService.getTransactions();
        return ResponseEntity.ok().body(userService.getTradedVolume(dates,id,transactions));
    }

}




