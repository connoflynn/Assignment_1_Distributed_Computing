package src;

import java.math.BigDecimal;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bank implements BankInterface {
    private List<Account> accounts = new ArrayList<>(); // users accounts
    private List<Session> sessions = new ArrayList<>(); // all sessions
    public Bank() throws RemoteException
    {
        super();
    }
    
    public Account createAccount(String username, String password) throws RemoteException {
    	int accNum = accounts.size() + 1;
        Account account = new Account(accNum, username, password);
    	accounts.add(account);
        System.out.println("New Account Created! Account number: " + account.getAccountNumber());
        return account;
    }
    // The login method returns a token that is valid for some time period that must be passed to the other methods as a session identifier
    public long login(String username, String password) throws RemoteException, InvalidLogin {
        for(Account account : accounts) {
            if(username.equals(account.getUsername()) && password.equals(account.getPassword())) {
                Session session = new Session(generateSessionID(), account.getAccountNumber(), LocalDateTime.now());
                sessions.add(session);
                System.out.println(account.getUsername() + " logged in!");
                return session.getSessionID();
            }
        }
    	throw new InvalidLogin();
    }
    public void deposit(int accountNum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
        for(Session session : sessions){
            if(sessionID == session.getSessionID()){
                if(LocalDateTime.now().isAfter(session.getValidUntil())){
                    throw new InvalidSession();
                }
                else{
                    for(Account account : accounts) {
                        if(accountNum == account.getAccountNumber()) {
                            account.newTransaction(new Transaction(amount, LocalDateTime.now(), "Deposit"));
                            account.setBalance(account.getBalance().add(amount));
                            System.out.println(amount + " deposited to account " + accountNum);
                            return;
                        }
                    }
                }
            }
        }
    	throw new InvalidSession();
    }
    public boolean withdraw(int accountNum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
        for(Session session : sessions){
            if(sessionID == session.getSessionID()){
                if(LocalDateTime.now().isAfter(session.getValidUntil())){
                    throw new InvalidSession();
                }
                else{
                    for(Account account: accounts) {
                        if(accountNum == account.getAccountNumber()) {
                            BigDecimal balance = account.getBalance();
                            if(amount.compareTo(balance) > 0) {
                                System.out.println("Invalid Funds!");
                                return false;
                            }
                            else {
                                account.newTransaction(new Transaction(amount, LocalDateTime.now(), "Withdraw"));
                                account.setBalance(account.getBalance().subtract(amount));
                                System.out.println(amount + " withdrawn from account " + accountNum);
                                return true;
                            }
                        }
                    }
                }
            }
        }
    	throw new InvalidSession();
    }
    public BigDecimal getBalance(int accountNum, long sessionID) throws RemoteException, InvalidSession {
        for(Session session : sessions){
            if(sessionID == session.getSessionID()){
                if(LocalDateTime.now().isAfter(session.getValidUntil())){
                    throw new InvalidSession();
                }
                else{
                    for(Account account: accounts) {
                        if(accountNum == account.getAccountNumber()) {
                            return account.getBalance();
                        }
                    }
                }
            }
        }
        throw new InvalidSession();
    }
    public Statement getStatement(LocalDateTime from, LocalDateTime to, long sessionID, int accNum) throws RemoteException, InvalidSession {
        for(Session session : sessions){
            if(sessionID == session.getSessionID()){
                if(LocalDateTime.now().isAfter(session.getValidUntil())){
                    throw new InvalidSession();
                }
                else{
                    for(Account account: accounts) {
                        if(accNum == account.getAccountNumber()) {
                            List<Transaction> validTransactions = new ArrayList<>();
                            List<Transaction> transactions = account.getTransactions();
                            for(Transaction transaction : transactions){
                                if(transaction.getDate().isAfter(from) && transaction.getDate().isBefore(to)){
                                    validTransactions.add(transaction);
                                }
                            }
                            Statement statement = new Statement(accNum, account.getUsername(), from, to, validTransactions);
                            System.out.print("Statement Requested for account " + accNum);
                            return statement;
                        }
                    }
                }
            }
        }
        throw new InvalidSession();
    }

    public long generateSessionID(){
        long min = 1000000;
        long max = 10000000;
        long sessionID = (long) (Math.random() * (max - min + 1) + min);
        return sessionID;
    }

    public static void main(String args[]) throws Exception {
    	try{
            // First reset our Security manager
            if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
                    System.out.println("Security manager set");
                }

            // Create an instance of the local object
            BankInterface bankServer = new Bank();
            System.out.println("Instance of Bank created");
            BankInterface stub = (BankInterface) UnicastRemoteObject.exportObject(bankServer, 0);
            
            // Put the server object into the Registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Bank", stub);
            System.out.println("Name rebind completed");  
            System.out.println("Server ready for requests!");
          
        }
        catch(Exception exc)
        {
          System.out.println("Error in main - " + exc.toString());
        }
    }
}
