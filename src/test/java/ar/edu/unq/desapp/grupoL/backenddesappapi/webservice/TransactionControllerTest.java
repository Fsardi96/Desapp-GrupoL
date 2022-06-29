package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    TransactionService transactionService;

    @Test
    public void getTransactions() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        List<TransactionDTO> transactionsDTO = new ArrayList<TransactionDTO>();


        when(transactionService.getTransactions()).thenReturn(transactions);
        when(transactionService.transformTransactionsToDTO(transactions)).thenReturn(transactionsDTO);

        transactionController.getTransactions();

        verify(transactionService, atLeastOnce()).transformTransactionsToDTO(transactions);
        verify(transactionService, atLeastOnce()).getTransactions();
    }

    @Test
    public void getTransactionsByUserId() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        List<TransactionDTO> transactionsDTO = new ArrayList<TransactionDTO>();


        when(transactionService.getTransactionsByUserId(1L)).thenReturn(transactions);
        when(transactionService.transformTransactionsToDTO(transactions)).thenReturn(transactionsDTO);

        transactionController.getTransactionsByUserId(1L);

        verify(transactionService, atLeastOnce()).getTransactionsByUserId(1L);
    }

    @Test
    public void createTransaction() throws IOException {
        TransactionCreateDTO transactionCreateDTO = new TransactionCreateDTO();
        TransactionDTO transactionDTO = new TransactionDTO();
        Transaction transaction = new Transaction();

        when(transactionService.createTransaction(transactionCreateDTO, 1L)).thenReturn(transaction);
        when(transactionService.makeTransactionDTO(transaction)).thenReturn(transactionDTO);

        transactionController.createTransaction(1L, transactionCreateDTO);

        verify(transactionService, atLeastOnce()).createTransaction(transactionCreateDTO, 1L);
        verify(transactionService, atLeastOnce()).makeTransactionDTO(transaction);
    }

    @Test
    public void processTransaction() throws IOException {
        Transaction transaction = new Transaction();

        when(transactionService.processTransaction(1L, 1L)).thenReturn(transaction);

        transactionController.processTransaction(1L, 1L);

        verify(transactionService, atLeastOnce()).makeTransactionProcessedDTO(transaction);
    }
}
