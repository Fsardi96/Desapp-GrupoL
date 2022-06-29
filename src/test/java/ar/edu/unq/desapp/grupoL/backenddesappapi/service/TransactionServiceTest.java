package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    UserService userService;

    @Mock
    CryptoService cryptoService;

    @Test
    public void getTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();

        when(transactionRepository.findAll()).thenReturn(transactions);

        transactionService.getTransactions();

        verify(transactionRepository, atLeastOnce()).findAll();
    }

    @Test
    public void findTransaction() throws IOException {
        Transaction transaction = new Transaction();

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        transactionService.findTransaction(1L);

        verify(transactionRepository, atLeastOnce()).findById(1L);
    }

    @Test
    public void saveTransaction() throws IOException {
        TransactionCreateDTO transactionDTO = new TransactionCreateDTO("symbol",1f,"COMPRA");
        User user = new User("name", "surname", "email", "address",
                            "password", "cvu", "wallet");

        when(cryptoService.fetchCryptoPriceByEndpoint("symbol")).thenReturn(2f);
        when(cryptoService.getUSDPrice()).thenReturn(2f);
        when(userService.findUser(1L)).thenReturn(user);

        transactionService.createTransaction(transactionDTO, 1L);

        verify(transactionRepository, atLeastOnce()).save(any());
    }

    @Test
    public void getTransactionsByUser() {
        List<Transaction> transactions = new ArrayList<Transaction>();

        when(transactionRepository.getTransactionsByUserId(1L)).thenReturn(transactions);

        transactionService.getTransactionsByUserId(1L);

        verify(transactionRepository, atLeastOnce()).getTransactionsByUserId(1L);
    }
}