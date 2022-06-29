package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String errorMessage) {
        super(errorMessage);
        this.setStackTrace(new StackTraceElement[0]);
    }

}