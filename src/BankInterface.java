package src;

import java.rmi.Remote;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.rmi.*;

public interface BankInterface extends Remote
{
    public Account createAccount(String username, String password) throws RemoteException;
    // The login method returns a token that is valid for some time period that must be passed to the other methods as a session identifier
    public long login(String username, String password) throws RemoteException, InvalidLogin;
    public void deposit(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession;
    public boolean withdraw(int accountnum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession;
    public BigDecimal getBalance(int accountnum, long sessionID) throws RemoteException, InvalidSession;
    public Statement getStatement(LocalDateTime from, LocalDateTime to, long sessionID, int accNum) throws RemoteException, InvalidSession;
}
