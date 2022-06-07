package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

public class TransactionNotFound extends RuntimeException{
    public TransactionNotFound() {
        super("There is not a transaction with that ID");
        this.setStackTrace(new StackTraceElement[0]);
    }
}
