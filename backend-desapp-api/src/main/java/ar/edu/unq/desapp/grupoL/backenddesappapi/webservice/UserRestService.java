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


//@RequestMapping("/user")

@RestController
@Transactional
public class UserRestService {
    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<?> getUsers(){
        ArrayList<User> userList = userService.getUsers();
        return ResponseEntity.ok().body(userList);
    }

    @PostMapping()
    public User createUser(@RequestBody User usuario){
        return this.userService.createUser(usuario);
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




