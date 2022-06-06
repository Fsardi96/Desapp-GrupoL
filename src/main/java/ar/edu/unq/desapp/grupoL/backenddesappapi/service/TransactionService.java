package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import ar.edu.unq.desapp.grupoL.backenddesappapi.Helpers.CurrentDateTime;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionCreateDTO;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos.TransactionDTO;
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

    public List<TransactionDTO> processTransactionsToDTO(List<Transaction> retrievedTransactions) {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();

        retrievedTransactions.stream().forEach((transaction) -> {
            transactionDTOS.add(new TransactionDTO(transaction.getId(), transaction.getDateAndTime().toString(), transaction.getCrypto().getSymbol(), transaction.getAmountOfCrypto(),
                    transaction.getPriceOfCrypto(), transaction.getFinalPriceInARS(), transaction.getTransactionType(),
                    transaction.getUser().getName() + " " + transaction.getUser().getSurname(), transaction.getUser().getOperationsNumber(),
                    transaction.getUser().getScore()));
        });
        return transactionDTOS;
    }

    public void deleteTransaction(Long id) {
        this.transactionRepository.deleteById(id);
    }

    public void processTransaction(Transaction transaction, Long secondaryUserID) {
        transaction.process();
        User secondaryUser = userService.findUser(secondaryUserID);
        long difference = ChronoUnit.MINUTES.between(LocalDateTime.now(), transaction.getDateAndTime());
        if(difference <= 30){
            transaction.getUser().setScore(transaction.getUser().getScore() + 10);    //TODO: Score es un string, se debe parsear el score a int, sumarlo y volverlo a parsear a String.
            secondaryUser.setScore(transaction.getUser().getScore() + 10);
        }else {
            transaction.getUser().setScore(transaction.getUser().getScore() + 5);
            secondaryUser.setScore(transaction.getUser().getScore() + 5);
        }
        userService.updateUser(transaction.getUser());
        userService.updateUser(secondaryUser);

        //TODO: CAMBIAR EL ESTADO DE LA TRANSACCIÓN A "PROCESADA" y hacerle un SAVE
    }

    public void cancelTransaction(Long transactionID) {
        Transaction transaction = this.findTransaction(transactionID);
        transaction.cancel();

        //TODO: CAMBIAR EL ESTADO DE LA TRANSACCIÓN A "Cancelada" y hacerle un SAVE

        // this.transactionRepository.deleteById(transactionID);
    }
}
