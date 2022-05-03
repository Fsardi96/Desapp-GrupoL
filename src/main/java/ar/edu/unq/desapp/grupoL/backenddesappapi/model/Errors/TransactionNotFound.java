package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

public class TransactionNotFound extends RuntimeException{
    public TransactionNotFound(String errorMessage) {
        super(errorMessage);
        this.setStackTrace(new StackTraceElement[0]);
    }
}
