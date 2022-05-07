package ar.edu.unq.desapp.grupoL.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.Transaction;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Configuration
@Repository
public interface  TransactionRepository  extends CrudRepository<Transaction,Long> {

    List<Transaction> findAll();

    @Query(value = "SELECT * FROM TRANSACTION t WHERE t.user = :id", nativeQuery = true)
    List<Transaction> getTransactionsByUserId(@Param("id") Long id);
}
