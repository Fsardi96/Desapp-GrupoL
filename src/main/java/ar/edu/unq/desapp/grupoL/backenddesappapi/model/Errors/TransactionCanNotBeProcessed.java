package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

public class TransactionCanNotBeProcessed extends RuntimeException{
    public TransactionCanNotBeProcessed() {
        super("The transaction is already processed");
        this.setStackTrace(new StackTraceElement[0]);
    }
}