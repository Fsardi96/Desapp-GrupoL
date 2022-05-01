package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.*;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserError;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.*;


//@RequestMapping("/user")

@RestController
@Transactional
@RequestMapping("/api") //method = RequestMethod.GET)
public class UserController {
    @Autowired
    private UserService userService;
    private RestTemplate restTemplate = new RestTemplate() ;



    @GetMapping("/users")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id){
        User userFound = userService.findUser(id);
        return ResponseEntity.ok().body(userFound);
    }

    @GetMapping("/userTransactions/{id}")
    public ResponseEntity<?> getUserTransactions(@PathVariable Long id){
        User userFound = userService.findUser(id); //crear validación si el user existe.
        return ResponseEntity.ok().body(userFound.getTransactions());
    }

    @GetMapping("/getCrypoValue/{symbol}")
    public ResponseEntity<?> getCryptoCurrencyValue(@PathVariable String symbol){
        CryptoCurrency entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + symbol, CryptoCurrency.class);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if (entity != null) {
            entity.setLastUpdateDateAndTime(formatter.format(date));
        }
        return ResponseEntity.ok().body(entity);
    }


    @GetMapping("/getCrypoValue/all")
    public ResponseEntity<?> getAllCryptoCurrencyPrices() {
        CryptoCurrencyList list = new CryptoCurrencyList();
        for (CryptoCurrencyEnum crypto : CryptoCurrencyEnum.values()) {
            CryptoCurrency entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + crypto.name(), CryptoCurrency.class);
            if (entity != null) {
                entity.setLastUpdateDateAndTime(userService.getNewDate());
            }
            list.addCrypto(entity);

        }
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user) throws UserError{
        return this.userService.createUser(user);
    }

    @PostMapping(path="/addTransaction/user={userID}",  consumes = "application/json", produces = "application/json")
    public void createTransaction(@PathVariable Long userID, @RequestBody TransactionDTO transaction) {
        User user = this.userService.findUser(userID);
        //UserDTO userDTO = new UserDTO(user.getId(),user.getName(),user.getSurname());
        Transaction newTransaction = new Transaction(userService.getNewDate(), transaction.getCrypto(), transaction.getAmountOfCrypto(),transaction.getPriceOfCryto(),
                                                    user, transaction.getTransactionType());


        userService.addTransactionToUser(user,newTransaction);
       // return newTransaction; //debe devolver un transactionDTO para ocultar los datos enteros del user.
    }


    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
    }

    /*@RequestMapping(value = "/api/version", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getVersion() {
        String version = "0.2.2";
        Map<String, String> resultado = new HashMap<String, String>();
        resultado.put("version", version);
        return ResponseEntity.ok().body(resultado);
    }*/

}




