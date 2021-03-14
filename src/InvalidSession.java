package src;

public class InvalidSession extends Exception {

    public InvalidSession() {
        super("Invalid Session or Session Expired!");
    }

}