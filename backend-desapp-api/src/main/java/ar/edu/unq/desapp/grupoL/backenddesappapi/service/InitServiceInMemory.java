/*
package ar.edu.unq.desapp.grupoL.backenddesappapi.service;

import javax.annotation.PostConstruct;

import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class InitServiceInMemory {

    protected final Log logger = LogFactory.getLog(getClass());

    @Value("${spring.datasource.driverClassName:NONE}")
    private String className;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initialize() {
        if (className.equals("org.h2.Driver")) {
            logger.info("Init Data Using H2 DB");
            fireInitialData();
        }
    }

    private void fireInitialData() {
        User user = new User(1L, "PNA 879", "Renault Clio","asd","asd","asd","sd","sd");
        userService.createUser(user);
        User user2 = new User(2L, "PNA dd", "asd Clio","ddd","ddd","dd","sddd","sddd");
        userService.createUser(user2);

    }
}
*/
