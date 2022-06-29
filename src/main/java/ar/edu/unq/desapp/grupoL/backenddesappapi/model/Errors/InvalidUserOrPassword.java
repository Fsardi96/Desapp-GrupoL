package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Errors;

public class InvalidUserOrPassword extends RuntimeException {
    public InvalidUserOrPassword(){
        super("Invalid User Or Password");
        this.setStackTrace(new StackTraceElement[0]);
    }
}
