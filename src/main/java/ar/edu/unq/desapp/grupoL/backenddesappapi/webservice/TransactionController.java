package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;


import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionProcessedDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Api(tags = "Transaction services")
@Tag(name = "Transaction services", description = "Manage transactions")
@RestController
@RequestMapping("/api") //method = RequestMethod.GET)
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @Operation(summary = "Get all transactions")
    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(){
        return this.transactionService.transformTransactionsToDTO(transactionService.getTransactions());
    }


    @Operation(summary = "Get user transactions")
    @GetMapping("/transactionsByUserId/{id}")
        public List<TransactionDTO> getTransactionsByUserId(@Parameter(description = "The user ID that needs to be fetched")
                                                                @PathVariable Long id) {
         transactionService.getTransactionsByUserId(id);
         return this.transactionService.transformTransactionsToDTO(transactionService.getTransactionsByUserId(id));
    }


    @Operation(summary = "Create user transaction")
    @PostMapping(path="/addTransaction/user={userID}",  consumes = "application/json", produces = "application/json")
    public ResponseEntity<TransactionDTO> createTransaction(@Parameter(description = "The user ID") @PathVariable Long userID,
                                                            @Parameter(description = "The transaction to be registered")
                                            @RequestBody TransactionCreateDTO transaction) throws IOException {

        Transaction newTransaction = transactionService.createTransaction(transaction, userID);
        TransactionDTO  transactionDTO = transactionService.makeTransactionDTO(newTransaction);

        return ResponseEntity.ok().body(transactionDTO);
    }

    @Operation(summary =  "Process a transaction")
    @PostMapping(path="/processTransaction/transaction={transactionID}/secondaryUser={userID}", produces = "application/json")
    public ResponseEntity<TransactionProcessedDTO> processTransaction(@Parameter(description = "The transaction ID") @PathVariable Long transactionID,
                                                     @Parameter(description = "The user ID") @PathVariable Long userID) {


        Transaction transactionProcessed = transactionService.processTransaction(transactionID, userID);
        return ResponseEntity.ok().body(transactionService.makeTransactionProcessedDTO(transactionProcessed));
    }


    @Operation(summary =  "Cancel a transaction")
    @DeleteMapping(path="/cancelTransaction/transaction={transactionID}")
    public ResponseEntity<String> cancelTransaction(@Parameter(description = "The transaction ID") @PathVariable Long transactionID) {
        transactionService.cancelTransaction(transactionID);
        return ResponseEntity.ok().body("The transaction was cancelled successfully");
    }

}
