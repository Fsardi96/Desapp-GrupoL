package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

public class ProccesingWithSameUser extends RuntimeException{
    public ProccesingWithSameUser() {
        super("You are trying to proccess a transaction with the same users");
        this.setStackTrace(new StackTraceElement[0]);
    }
}