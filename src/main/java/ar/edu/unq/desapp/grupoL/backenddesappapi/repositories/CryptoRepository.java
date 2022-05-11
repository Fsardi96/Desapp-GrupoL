package ar.edu.unq.desapp.grupoL.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptoCurrency;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Configuration
@Repository
public interface CryptoRepository extends CrudRepository<CryptoCurrency,String> { }
