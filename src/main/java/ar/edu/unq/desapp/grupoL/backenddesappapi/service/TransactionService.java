package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.Helpers.CurrentDateTime;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.ProccesingWithSameUser;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.TransactionCanNotBeProcessed;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserAlreadyExists;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.UserNotFound;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserService userService;

    @Autowired
    CryptoService cryptoService;

    public TransactionService() {
        //Empty constructor
    }

    @Transactional
    public List<Transaction> getTransactions(){
        return this.transactionRepository.findAll();
    }

    @Transactional
    public Transaction findTransaction(Long id){
        return  transactionRepository.findById(id).get();
    }

    @Transactional
    public Transaction createTransaction(TransactionCreateDTO transaction, Long userID) throws IOException {

        CryptoCurrency crypto = cryptoService.findCrypto(transaction.getCryptoSymbol());
        float finalDolarInARS = cryptoService.getUSDPrice() * crypto.getPrice();
        crypto.setPriceInARS(finalDolarInARS );
        crypto.setAmount(transaction.getAmountOfCrypto());
        User user = userService.findUser(userID);
        Transaction newTransaction = new Transaction(crypto,
                user,transaction.getTransactionType());
        return this.transactionRepository.save(newTransaction);
    }

    public List<Transaction> getTransactionsByUserId(Long id) {
        return this.transactionRepository.getTransactionsByUserId(id);
    }

    @Transactional
    public void updateTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }

    public List<TransactionDTO> processTransactionsToDTO(List<Transaction> retrievedTransactions) {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();

        retrievedTransactions.stream().forEach((transaction) -> {
            transactionDTOS.add(new TransactionDTO(transaction.getId(), transaction.getDateAndTime().toString(), transaction.getCrypto().getSymbol(), transaction.getAmountOfCrypto(),
                    transaction.getPriceOfCrypto(), transaction.getFinalPriceInARS(), transaction.getTransactionType(),
                    transaction.getUser().getName() + " " + transaction.getUser().getSurname(), transaction.getUser().getOperationsNumber(),
                    transaction.getUser().getScore(),transaction.getStatus()));
        });
        return transactionDTOS;
    }

    public void deleteTransaction(Long id) {
        this.transactionRepository.deleteById(id);
    }




    public Transaction processTransaction(Long transactionID, Long secondaryUserID) {

        if(this.isValidTransaction(transactionID)) {
            Transaction transaction = this.findTransaction(transactionID);
            if( this.areValidUsers(transaction,secondaryUserID)) {
                User secondaryUser = userService.findUser(secondaryUserID);
                transaction.process(secondaryUser);

                userService.updateUser(transaction.getUser());
                userService.updateUser(secondaryUser);

                this.updateTransaction(transaction);
                return transaction;
            }
        }
    }

    private boolean areValidUsers(Transaction transaction, Long secondaryUserID){
        if(transaction.getUser().getId().equals(secondaryUserID)){
            throw new ProccesingWithSameUser();
        }

        if(userService.findUser(secondaryUserID) == null){
            throw new UserNotFound();
        }

    }

    private boolean isValidTransaction(Transaction transaction, Long secondaryUserID) {

        if(transaction.getStatus().equals("Procesada")){
            throw new TransactionCanNotBeProcessed();
        }
    }


    public void cancelTransaction(Long transactionID) {
        //TODO VALIDAR QUE EXISTE LA TRANSACTINO
        Transaction transaction = this.findTransaction(transactionID);
        transaction.cancel();
    }
}
