package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.DatesDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserTradedVolumeDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserError;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Api(tags = "User services")
@Tag(name = "User services", description = "Manage users")
@RestController
@Transactional
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;

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
    @PostMapping(path="/addUser" , consumes = "application/json", produces = "application/json")
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




