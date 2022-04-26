package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

public class UserError extends RuntimeException {
    public UserError(String errorMessage) {
        super(errorMessage);
        this.setStackTrace(new StackTraceElement[0]);
    }
}
