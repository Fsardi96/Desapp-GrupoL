package ar.edu.unq.desapp.grupoL.backenddesappapi.repositories;


import ar.edu.unq.desapp.grupoL.backenddesappapi.model.CryptosPrice;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Configuration
@Repository
public interface CryptosPriceRepository extends CrudRepository<CryptosPrice,Long> {

    @Query(value = "SELECT CAST(COUNT(1) AS BIT) FROM CRYPTOS_PRICE", nativeQuery = true)
    Boolean anyRecord();


    @Query(value = "SELECT * FROM CRYPTOS_PRICE c WHERE c.id =(SELECT MAX(id) FROM CRYPTOS_PRICE)", nativeQuery = true)
        CryptosPrice getPriceOf(String cryptoSymbol);
}



   /* @Query(value = "SELECT cryptoSymbol FROM CRYPTOSPRICE c WHERE c.ID = :(SELECT MAX(ID) FROM CRYPTOSPRICE)", nativeQuery = true)
    Float getPriceOf(String cryptoSymbol);
    */