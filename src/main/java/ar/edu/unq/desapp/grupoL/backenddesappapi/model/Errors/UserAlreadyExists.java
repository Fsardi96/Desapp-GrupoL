package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;


public class UserAlreadyExists  extends RuntimeException {
    public UserAlreadyExists(){
        super("User already exists with the same Wallet");
        this.setStackTrace(new StackTraceElement[0]);
    }
}
