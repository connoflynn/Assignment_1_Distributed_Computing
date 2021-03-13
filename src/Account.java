package src;

import java.math.BigDecimal;
import java.util.List;

public class Account {
	private int accountNumber;
    private String username;
    private String password;
    private BigDecimal balance;
    private List<Transaction> transactions;
    
    
    public Account(int accNum, String username, String password, BigDecimal balance, List<Transaction> transactions) {
    	this.accountNumber = accNum;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.transactions = transactions;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public void newTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
    
}
