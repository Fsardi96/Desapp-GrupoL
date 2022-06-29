package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.DatesDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.CryptosPriceService;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private TransactionService transactionService;

    @Test
    public void getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        when(userService.getUsers()).thenReturn(users);

        userController.getUsers();

        verify(userService, atLeastOnce()).getUsers();
    }

    @Test
    public void getUserById() {
        User user = new User();

        when(userService.findUser(1L)).thenReturn(user);

        userController.getUserByID(1L);

        verify(userService, atLeastOnce()).findUser(1L);
    }

    @Test
    public void registerUser() {
        UserCreateDTO dto = new UserCreateDTO();
        User user = new User();

        when(userService.createUser(dto)).thenReturn(user);

        userController.createUser(dto);

        verify(userService, atLeastOnce()).createUser(dto);
    }

    @Test
    public void deleteUser() {
        List<Transaction> transactions = new ArrayList<Transaction>();

        when(transactionService.getTransactionsByUserId(1L)).thenReturn(transactions);

        userController.deleteUser(1L);

        verify(userService, atLeastOnce()).deleteUser(1L);
    }

    @Test
    public void getTradedVolume() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        DatesDTO dates = new DatesDTO();

        when(transactionService.getTransactions()).thenReturn(transactions);

        userController.getTradedVolume(1L, dates);

        verify(userService, atLeastOnce()).getTradedVolume(dates,1L, transactions);
    }
}
