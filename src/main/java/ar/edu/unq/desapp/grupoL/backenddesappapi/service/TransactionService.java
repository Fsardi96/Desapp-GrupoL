package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptosPrice;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionProcessedDTO;
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
    CryptosPriceService cryptosPriceService;
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

        //if( cryptosPriceService.isThereAnyRecord()){
        //String priceDB = cryptosPriceService.fetchCryptoPriceByDB(transaction.getCryptoSymbol());
         //System.out.println("precio buscado: " + priceDB);

        Float price = cryptoService.fetchCryptoPriceByEndpoint(transaction.getCryptoSymbol());
        Float finalDolarInARS = cryptoService.getUSDPrice() * price;
        User user = userService.findUser(userID);
        Float amountOfCrypto = transaction.getAmountOfCrypto();
        Transaction newTransaction = new Transaction(transaction.getCryptoSymbol(),
                user,transaction.getTransactionType(),amountOfCrypto,price * amountOfCrypto,finalDolarInARS * amountOfCrypto);
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
            transactionDTOS.add(new TransactionDTO(transaction.getId(), transaction.getDateAndTime().toString(), transaction.getCrypto(), transaction.getAmountOfCrypto(),
                    transaction.getPriceOfCrypto(), transaction.getFinalPriceInARS(), transaction.getTransactionType(),
                    transaction.getUser().getName() + " " + transaction.getUser().getSurname(), transaction.getUser().getOperationsNumber(),
                    transaction.getUser().getScore(),transaction.getStatus()));
        });
        return transactionDTOS;
    }


    @Transactional
    public Transaction processTransaction(Long transactionID, Long secondaryUserID) {

        Transaction transaction = this.findTransaction(transactionID);
        User secondaryUser = userService.findUser(secondaryUserID);
        if(this.canProcessTransaction(transaction, secondaryUserID)) {
            transaction.process(secondaryUser);

            userService.updateUser(transaction.getUser());
            userService.updateUser(secondaryUser);

            transaction.setSecondaryUser(secondaryUser);

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

    @Transactional
    public void cancelTransaction(Long transactionID) {
        Transaction transaction = this.findTransaction(transactionID);
        transaction.cancel();
    }

    public TransactionDTO makeTransactionDTO(Transaction newTransaction) {

        String username = newTransaction.getUser().getFullName();
        Integer operationNumber = newTransaction.getUser().getOperationsNumber();
        String score = newTransaction.getUser().getScore();

       return new TransactionDTO(newTransaction.getId(), newTransaction.getDateAndTime().toString(),
               newTransaction.getCrypto(), newTransaction.getAmountOfCrypto(),
               newTransaction.getPriceOfCrypto(), newTransaction.getFinalPriceInARS(),
               newTransaction.getTransactionType(), username, operationNumber, score,newTransaction.getStatus());
    }

    public TransactionProcessedDTO makeTransactionProcessedDTO(Transaction transactionProcessed) {

       return new TransactionProcessedDTO(transactionProcessed.getCrypto(),
                transactionProcessed.getAmountOfCrypto(),
                transactionProcessed.getPriceOfCrypto(),
                transactionProcessed.getFinalPriceInARS(),
                transactionProcessed.getUser().getFullName(),
                transactionProcessed.getUser().getOperationsNumber(),
                transactionProcessed.getUser().getScore(), transactionProcessed.getAddress(),transactionProcessed.getStatus());
    }


}