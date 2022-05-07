package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.Helpers.CurrentDateTime;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserError;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@Transactional
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/users")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Long id){
        User userFound = userService.findUser(id);
        return ResponseEntity.ok().body(userFound);
    }

    @GetMapping("/getCrypoValue/{symbol}")
    public ResponseEntity<CryptoCurrency> getCryptoCurrencyValue(@PathVariable String symbol){
        CryptoCurrency entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + symbol, CryptoCurrency.class);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if (entity != null) {
            entity.setLastUpdateDateAndTime(formatter.format(date));
        }
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/getCrypoValue/all")
    public ResponseEntity<CryptoCurrencyList> getAllCryptoCurrencyPrices() {
        CryptoCurrencyList list = new CryptoCurrencyList();
        for (CryptoCurrencyEnum crypto : CryptoCurrencyEnum.values()) {
            CryptoCurrency entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + crypto.name(), CryptoCurrency.class);
            if (entity != null) {
                entity.setLastUpdateDateAndTime(CurrentDateTime.getNewDateString());
            }
            list.addCrypto(entity);

        }
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(path="/addUser" , consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody User user) throws UserError{
        return this.userService.createUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        List<Transaction> transactionsByID = this.transactionService.getTransactionsByUserId(id);
        if(transactionsByID.size() > 0) {
            transactionsByID.forEach(transaction -> this.transactionService.deleteTransaction(transaction.getId()));
        }else{
            this.userService.deleteUser(id);
        }
    }
}




