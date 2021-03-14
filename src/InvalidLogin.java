package src;

public class InvalidLogin extends Exception{

    public InvalidLogin() {
        super("Invalid Login Details!");
    }
}
