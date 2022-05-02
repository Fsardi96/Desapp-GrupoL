package ar.edu.unq.desapp.grupoL.backenddesappapi.webservice;


import ar.edu.unq.desapp.grupoL.backenddesappapi.Helpers.CurrentDateTime;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoL.backenddesappapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequestMapping("/api") //method = RequestMethod.GET)
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @PostMapping(path="/addTransaction/user={userID}",  consumes = "application/json", produces = "application/json")
    public Transaction createTransaction(@PathVariable Long userID, @RequestBody Transaction transaction) {
        User user = this.userService.findUser(userID);
        Transaction transaction1 = transactionService.createTransaction(transaction,user);

        // crear TransactionDTO
        return transaction1; //debe devolver un transactionDTO para ocultar los datos enteros del user.

/*        User user = this.userService.findUser(userID);
        String username = user.getName() + " " + user.getSurname();
        TransactionDTO dto = new TransactionDTO(transaction.getCrypto(), transaction.getAmountOfCrypto(),
                transaction.getPriceOfCrypto(), transaction.getPriceInARS(), transaction.getTransactionType(), username);
        return dto;*/
    }

}
