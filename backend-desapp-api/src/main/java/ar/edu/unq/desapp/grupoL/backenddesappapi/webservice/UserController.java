package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrencyValue;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;


//@RequestMapping("/user")

@RestController
@Transactional
@RequestMapping("/api") //method = RequestMethod.GET)
public class UserController {
    @Autowired
    private UserService userService;


    private RestTemplate restTemplate = new RestTemplate() ;
    private CryptoCurrencyValue crypoCurrencyValue = new CryptoCurrencyValue();
    //private CryptoCurrency cryptoCurrency = new CryptoCurrency();


    @GetMapping("/users")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id){
        Optional<User> userFound = userService.findUser(id);
        return ResponseEntity.ok().body(userFound);
    }

    @GetMapping("/getCrypoValue/{symbol}")
    public CryptoCurrency getCryptoCurrencyValue(@PathVariable String symbol){
        CryptoCurrency entity = restTemplate.getForObject("https://api1.binance.com/api/v3/ticker/price?symbol=" + symbol, CryptoCurrency.class);
        return new CryptoCurrency(entity.getSymbol(),entity.getPrice(),"HoraActual");
    }

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user){
        return this.userService.createUser(user);
    }






   /* @DeleteMapping("/users/{id})
    public void deleteUser(@PathVariable Long id){
         this.userService.deleteUser(id);
         return ResponseEntity.ok().build();
    }*/



    /*@RequestMapping(value = "/api/version", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getVersion() {
        String version = "0.2.2";
        Map<String, String> resultado = new HashMap<String, String>();
        resultado.put("version", version);
        return ResponseEntity.ok().body(resultado);
    }*/

}




