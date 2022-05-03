package ar.edu.unq.desapp.grupoL.backenddesappapi.repositories;

import java.util.ArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;

@Configuration
@Repository
public interface UserRepository extends CrudRepository<User,Long>{ }
