package src;

import java.math.BigDecimal;
import java.rmi.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ATM {
    public static void main (String args[]) throws Exception {
        try {
			BankInterface bankServer = (BankInterface) Naming.lookup("//localhost/Bank");

            switch(args.length){
                case 3:
                    String username = args[1];
                    String password = args[2];
                    if(args[0].equals("create")){
                        Account account = bankServer.createAccount(username, password);
                        System.out.print("Account Created! Account Number: " + account.getAccountNumber() + " Username: " + account.getUsername());
                        break;
                    }
                    else if(args[0].equals("login")){
                        long sessionID = bankServer.login(username, password);
                        System.out.println("Successfully Logged In!");
                        System.out.println("Session ID: " + sessionID);
                        break;
                    }
                    else{
                        int sessionID = Integer.parseInt(args[0]);
                        int accNum = Integer.parseInt(args[2]); 
                        if(args[1].equals("balance")){
                            BigDecimal balance= bankServer.getBalance(accNum, sessionID);
                            System.out.println("Current Balance for Account " + accNum + ": " + balance);
                            break;
                        }
                        else{
                            System.out.println("Invalid Arguments!");
                            break;
                        }
                    }
                case 4:
                    int sessionID = Integer.parseInt(args[0]);
                    int accNum = Integer.parseInt(args[2]); 
                    BigDecimal amount = new BigDecimal(args[3]);
                    if(args[1].equals("deposit")){
                        bankServer.deposit(accNum, amount, sessionID);
                        System.out.println("Successfully deposited " + amount + " to account " + accNum);
                        break;
                    }
                    else if(args[1].equals("withdraw")){
                        boolean success = bankServer.withdraw(accNum, amount, sessionID);
                        if(success){
                            System.out.println("Successfully withdrawn " + amount + " from account " + accNum);
                        }
                        else{
                            System.out.println("Invalid Funds!");
                        }
                        break;
                    }
                    else{
                        System.out.println("Invalid Arguments!");
                        break;
                    }
                case 5:
                    int sID = Integer.parseInt(args[0]);
                    int aNum = Integer.parseInt(args[2]);
                    
                    String startDateString = args[3];
                    startDateString += " 00:00";
                    String endDateString = args[4];
                    endDateString += " 00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 

                    LocalDateTime startDate = LocalDateTime.parse(startDateString, formatter);
                    LocalDateTime endDate = LocalDateTime.parse(endDateString, formatter);

                    if(args[1].equals("statement")){
                        Statement statement = bankServer.getStatement(startDate, endDate, sID, aNum);
                        List<Transaction> transactions = statement.getTransactions();
                        for(Transaction transaction : transactions){
                            System.out.println("Date: " + transaction.getDate().toLocalDate() + " Time: " + transaction.getDate().getHour() +":"+transaction.getDate().getMinute() + " Transaction: " + transaction.getDescription() + " " + transaction.getAmount());
                        }
                        break;
                    }
                    break;
                default:
                    System.out.println("Invalid Arguments!");
                
            }
		}
		catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
