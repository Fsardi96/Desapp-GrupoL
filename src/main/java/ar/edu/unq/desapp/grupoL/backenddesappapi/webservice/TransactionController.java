package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(tags = "Transaction services")
@Tag(name = "Transaction services", description = "Manage transactions")
@RestController
@Transactional
@RequestMapping("/api") //method = RequestMethod.GET)
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @Operation(summary = "Get all transactions")
    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(){
        return this.transactionService.processTransactionsToDTO(transactionService.getTransactions());
    }

    @Operation(summary = "Get user transactions")
    @GetMapping("/transactionsByUserId/{id}")
        public List<TransactionDTO> getTransactionsByUserId(@Parameter(description = "The user ID that needs to be fetched")
                                                                @PathVariable Long id) {
         transactionService.getTransactionsByUserId(id);
         return this.transactionService.processTransactionsToDTO(transactionService.getTransactionsByUserId(id));
    }

    @Operation(summary = "Create user transaction")
    @PostMapping(path="/addTransaction/user={userID}",  consumes = "application/json", produces = "application/json")
    public TransactionDTO createTransaction(@Parameter(description = "The user ID") @PathVariable Long userID,
                                            @Parameter(description = "The transaction to be registered")
                                            @RequestBody TransactionCreateDTO transaction) throws IOException {
        Transaction savedTransaction = transactionService.createTransaction(transaction, userID);

        String username = savedTransaction.getUser().getName() + " " + savedTransaction.getUser().getSurname();
        Integer operationNumber = savedTransaction.getUser().getOperationsNumber();
        String score = savedTransaction.getUser().getScore();
        CryptoCurrency crypto = savedTransaction.getCrypto();

        return new TransactionDTO(savedTransaction.getId(), savedTransaction.getDateAndTime(),
                crypto.getSymbol(), crypto.getAmount(),
                                    crypto.getPrice(), crypto.getPriceInARS(),
                                    savedTransaction.getTransactionType(), username, operationNumber, score);
    }

}
