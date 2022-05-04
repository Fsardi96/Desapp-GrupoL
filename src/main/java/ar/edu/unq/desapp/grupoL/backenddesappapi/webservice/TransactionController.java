package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;


import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/api") //method = RequestMethod.GET)
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(){
        return this.transactionService.processTransactionsToDTO(transactionService.getTransactions());
    }

    @GetMapping("/transactionsByUserId/{id}")
        public List<TransactionDTO> getTransactionsByUserId(@PathVariable Long id) {
         transactionService.getTransactionsByUserId(id);
         return this.transactionService.processTransactionsToDTO(transactionService.getTransactionsByUserId(id));
    }

    @PostMapping(path="/addTransaction/user={userID}",  consumes = "application/json", produces = "application/json")
    public TransactionDTO createTransaction(@PathVariable Long userID, @RequestBody Transaction transaction) {
        User user = this.userService.findUser(userID);
        String username = user.getName() + " " + user.getSurname();
        Transaction savedTransaction = transactionService.createTransaction(transaction,user);

        return new TransactionDTO(savedTransaction.getId(), savedTransaction.getDateAndTime(), savedTransaction.getCrypto(), savedTransaction.getAmountOfCrypto(),
                savedTransaction.getPriceOfCrypto(), savedTransaction.getPriceInARS(), savedTransaction.getTransactionType(),
                username, user.getOperationsNumber(), user.getScore());
    }

}
