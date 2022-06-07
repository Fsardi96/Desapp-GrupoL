package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors.*;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public Transaction findTransaction(Long id) {
        return transactionRepository.findById(id).orElseThrow(TransactionNotFound::new);
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

    @Transactional
    public List<Transaction> getTransactionsByUserId(Long id) {
        return this.transactionRepository.getTransactionsByUserId(id);
    }

    @Transactional
    public void deleteTransaction(Long id) {
        this.transactionRepository.deleteById(id);
    }

    @Transactional
    public void updateTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }

    public List<TransactionDTO> transformTransactionsToDTO(List<Transaction> retrievedTransactions) {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();

        retrievedTransactions.stream().forEach((transaction) -> {
            transactionDTOS.add(new TransactionDTO(transaction.getId(), transaction.getDateAndTime().toString(), transaction.getCrypto().getSymbol(), transaction.getAmountOfCrypto(),
                    transaction.getPriceOfCrypto(), transaction.getFinalPriceInARS(), transaction.getTransactionType(),
                    transaction.getUser().getName() + " " + transaction.getUser().getSurname(), transaction.getUser().getOperationsNumber(),
                    transaction.getUser().getScore(),transaction.getStatus()));
        });
        return transactionDTOS;
    }



    public Transaction processTransaction(Long transactionID, Long secondaryUserID) {

        Transaction transaction = this.findTransaction(transactionID);
        User secondaryUser = userService.findUser(secondaryUserID);
        if(this.canProcessTransaction(transaction, secondaryUserID)) {
            transaction.process(secondaryUser);

            userService.updateUser(transaction.getUser());
            userService.updateUser(secondaryUser);

            this.updateTransaction(transaction);
            return transaction;
        }
        throw new TransactionCanNotBeProcessed();
    }

    private boolean canProcessTransaction(Transaction transaction, Long secondaryUserID) {
        if (!this.isValidTransaction(transaction)){
            throw new TransactionCanNotBeProcessed();
        }
        if (!areValidUsers(transaction.getId(), secondaryUserID)){
            throw new ProccesingWithSameUser();
        }
        return true;
    }

    private boolean areValidUsers(Long firstUserID, Long secondaryUserID){
        return !firstUserID.equals(secondaryUserID);
    }

    private boolean isValidTransaction(Transaction transaction) {
        return transaction.getStatus().equals("En curso");
    }

    public void cancelTransaction(Long transactionID) {
        Transaction transaction = this.findTransaction(transactionID);
        transaction.cancel();
    }
}