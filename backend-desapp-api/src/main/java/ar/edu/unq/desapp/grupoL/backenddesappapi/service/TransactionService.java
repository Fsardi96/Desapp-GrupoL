package ar.edu.unq.desapp.grupoL.backenddesappapi.service;


import ar.edu.unq.desapp.grupoL.backenddesappapi.Helpers.CurrentDateTime;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoL.backenddesappapi.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public TransactionService(){}

    @Transactional
    public ArrayList<Transaction> getTransactions(){return (ArrayList<Transaction>) this.transactionRepository.findAll();
    }

    @Transactional
    public Transaction findTransaction(Long id){return  transactionRepository.findById(id).get();   //.orElseThrow(TransactionNotFound::new);
    }


    @Transactional
    public Transaction createTransaction(Transaction transaction,User user) {

        Transaction newTransaction = new Transaction(CurrentDateTime.getNewDateString(), transaction.getCrypto(), transaction.getAmountOfCrypto(),transaction.getPriceOfCrypto(),
                                                    user,transaction.getTransactionType());
        //Transaction.setId(this.idgenerador+1);
        //idgenerador++;
        return this.transactionRepository.save(newTransaction);
    }




}
