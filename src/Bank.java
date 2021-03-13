package src;

import java.math.BigDecimal;
import java.rmi.*;
import java.util.Date;
import java.util.List;

public class Bank implements BankInterface {
    private List<Account> accounts; // users accounts
    public Bank() throws RemoteException
    {
        super();
    }
    public long login(String username, String password) throws RemoteException, InvalidLogin {

    }
    public void deposit(int account, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
    // implementation code
    }
    public void withdraw(int account, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
    // implementation code
    }
    public BigDecimal getBalance(int account, long sessionID) throws RemoteException, InvalidSession {
    // implementation code
    }
    public Statement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession {
    // implementation code
    }
    public static void main(String args[]) throws Exception {
    // initialise Bank server - see sample code in the notes and online RMI tutorials for details
    }
    }
