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
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


@Component
public class DatabaseInitializate  {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;



    @PostConstruct
    public void initializer() throws Exception {
        System.out.println("Inicializando Clases");

        /*-------------------------------*User*---------------------------------------------------------------- */


     /*   LocalDateTime fecha1 = LocalDateTime.now();
        LocalDateTime fecha2 = LocalDateTime.now();

        fecha1 = fecha1.plusMinutes(30); //Le sumo 30 min a la fecha 1

        long minutes = ChronoUnit.MINUTES.between(fecha2, fecha1);

        System.out.println(minutes);
*/
        String str = "2022-06-05 11:30:40";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        System.out.println(dateTime);


/*
        UserCreateDTO userDto1 = new UserCreateDTO("Juan","Perez","Perez@dessap.com","Catamarca 134","Facundo1.","12345678912345678912aa","1234567a");
        UserCreateDTO userDto2 = new UserCreateDTO("Martin","Garcia","Garcia@dessap.com","SanLuis 134","Facundo2.","12345678912345678912ab","1234567b");
        UserCreateDTO userDto3 = new UserCreateDTO("Fernando","Rodriguez","Rodriguez@dessap.com","Larrea 134","Facundo3.","12345678912345678912ac","1234567c");
        User user1 = userService.createUser(userDto1);
        User user2 = userService.createUser(userDto2);
        User user3 = userService.createUser(userDto3);

        //-------------------------------*Transaction*----------------------------------------------------------
        TransactionCreateDTO transaction1 = new TransactionCreateDTO("ALICEUSDT",10f,"COMPRA");
        TransactionCreateDTO transaction2 = new TransactionCreateDTO("MATICUSDT",10f,"VENTA");
        TransactionCreateDTO transaction3 = new TransactionCreateDTO("AXSUSDT",10f,"COMPRA");
        transactionService.createTransaction(transaction1,user1.getId());
        transactionService.createTransaction(transaction2,user2.getId());
        transactionService.createTransaction(transaction3,user3.getId());
        System.out.println("Fin de Inicialización");*/
    }
}