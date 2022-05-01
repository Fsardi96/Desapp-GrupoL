package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

import javax.management.RuntimeErrorException;

public class UserNotFound  extends RuntimeException {
    public UserNotFound(){
        super("There is not a registered user with that ID");
        this.setStackTrace(new StackTraceElement[0]);
    }
}
