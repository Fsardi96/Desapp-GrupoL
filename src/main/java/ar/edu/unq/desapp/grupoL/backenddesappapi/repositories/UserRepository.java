package ar.edu.unq.desapp.grupoL.backenddesappapi.repositories;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;

@Configuration
@Repository
public interface UserRepository extends CrudRepository<User,Long>{

    @Query(value = "SELECT CAST(COUNT(1) AS BIT) FROM USER u WHERE u.wallet = :wallet", nativeQuery = true)
    boolean existWallet(@Param("wallet") String wallet);
}
