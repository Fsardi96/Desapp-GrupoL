package ar.edu.unq.desapp.grupoL.backenddesappapi.repositories;


import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptosPrice;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Configuration
@Repository
public interface CryptosPriceRepository extends CrudRepository<CryptosPrice,Long> {
}
