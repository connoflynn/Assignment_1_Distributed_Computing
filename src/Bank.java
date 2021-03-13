package src;

import src.exception.InvalidLogin;
import src.exception.InvalidSession;

import java.math.BigDecimal;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

public class Bank implements BankInterface {
    private static List<Account> accounts; // users accounts
    public Bank() throws RemoteException
    {
        super();
    }
    
    public static void createAccount(String username, String password) {
    	int accNum = accounts.size()+ 1;
    	for (Account account : accounts) {
    		if(username == account.getUsername()) {
    			System.out.println("Username Already Exists!");
    			return;
    		}
    	}
    	accounts.add(new Account(accNum, username, password, new BigDecimal("0.00"), null));
    }
    // The login method returns a token that is valid for some time period that must be passed to the other methods as a session identifier
    public long login(String username, String password) throws RemoteException, InvalidLogin {
    	for(Account account : accounts) {
    		if(username == account.getUsername() && password == account.getPassword()) {
    			long sessionID = 12345;
    			return sessionID;
            }
    	}
    	throw new InvalidLogin();
    }
    public void deposit(int accountNum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
    	for(Account account : accounts) {
    		if(accountNum == account.getAccountNumber()) {
    			account.setBalance(account.getBalance().add(amount));
    			return;
    		}
    	}
    	throw new InvalidSession();
    }
    public void withdraw(int accountNum, BigDecimal amount, long sessionID) throws RemoteException, InvalidSession {
    	for(Account account: accounts) {
    		if(accountNum == account.getAccountNumber()) {
    			BigDecimal balance = account.getBalance();
    			if(amount.compareTo(balance) > 0) {
    				System.out.println("Invalid Funds!");
    			}
    			else {
    				account.setBalance(account.getBalance().subtract(amount));
    			}
    			return;
    		}
    	}
    	throw new InvalidSession();
    }
    public BigDecimal getBalance(int accountNum, long sessionID) throws RemoteException, InvalidSession {
    	for(Account account: accounts) {
    		if(accountNum == account.getAccountNumber()) {
    			return account.getBalance();
    		}
    	}
        throw new InvalidSession();
    }
    public Statement getStatement(Date from, Date to, long sessionID) throws RemoteException, InvalidSession {
        return null;
    }
    public static void main(String args[]) throws Exception {
    // initialise Bank server - see sample code in the notes and online RMI tutorials for details
    	try
        {
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
          
          System.out.println("Creating Accounts!");
          createAccount("test1", "password");
          createAccount("test2", "password");
          System.out.println("Accounts Created!");          
          
          System.out.println("Server ready for requests!");
          
          
        }
        catch(Exception exc)
        {
          System.out.println("Error in main - " + exc.toString());
        }
    }
}
