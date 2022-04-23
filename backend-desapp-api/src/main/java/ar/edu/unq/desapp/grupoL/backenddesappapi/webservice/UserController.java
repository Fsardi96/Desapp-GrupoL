package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


//@RequestMapping("/user")

@RestController
@Transactional
@RequestMapping("/api") //method = RequestMethod.GET)
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        ArrayList<User> userList = userService.getUsers();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id){
        Optional<User> userFound = userService.findUser(id);
        return ResponseEntity.ok().body(userFound);
    }

    @PostMapping("addUser")
    public User createUser(@RequestBody User user){
        return this.userService.createUser(user);
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




