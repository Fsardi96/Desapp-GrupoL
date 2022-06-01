package ar.edu.unq.desapp.grupoL.backenddesappapi;


import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatabaseInitializate implements CommandLineRunner {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;



    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("Inicializando Clases");

        /*-------------------------------*User*---------------------------------------------------------------- */

        UserCreateDTO userDto1 = new UserCreateDTO("Juan","Perez","Perez@dessap.com","Catamarca 134","Facundo1.","12345678912345678912aa","1234567a");
        UserCreateDTO userDto2 = new UserCreateDTO("Martin","Garcia","Garcia@dessap.com","SanLuis 134","Facundo2.","12345678912345678912ab","1234567b");
        UserCreateDTO userDto3 = new UserCreateDTO("Fernando","Rodriguez","Rodriguez@dessap.com","Larrea 134","Facundo3.","12345678912345678912ac","1234567c");
        User user1 = userService.createUser(userDto1);
        User user2 = userService.createUser(userDto2);
        User user3 = userService.createUser(userDto3);

        /*-------------------------------*Transaction*---------------------------------------------------------- */
        TransactionCreateDTO transaction1 = new TransactionCreateDTO("ALICEUSDT",10f,1000f,"COMPRA");

       // transactionService.createTransaction(transaction1,user);


        System.out.println("Fin de Inicialización");
    }
}