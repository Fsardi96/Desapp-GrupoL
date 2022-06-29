package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.UserCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        when(userRepository.findAll()).thenReturn(users);

        userService.getUsers();

        verify(userRepository, atLeastOnce()).findAll();
    }

    @Test
    public void findUser() {
        User user = new User("name", "surname", "email", "address",
                "password", "cvu", "wallet");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.findUser(1L);

        verify(userRepository, atLeastOnce()).findById(1L);
    }

    @Test
    public void createUser() {
        UserCreateDTO user = new UserCreateDTO("Pepe", "Garcia", "pepe@gmail.com",
                "Cordoba 5300", "Pepegarcia123.", "12345678912345678912as", "1234567d");

        userService.createUser(user);

        verify(userRepository, atLeastOnce()).save(any());
    }
}
