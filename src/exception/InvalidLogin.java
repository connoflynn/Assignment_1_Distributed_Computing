package src.exception;

public class InvalidLogin extends Throwable{

    public InvalidLogin() {
        super("Invalid Login Details!");
    }
}
