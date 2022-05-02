package ar.edu.unq.desapp.grupoL.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Configuration
@Repository
public interface TransactionRepository  extends CrudRepository<Transaction,Long> {

    List<Transaction> findAll();
}
