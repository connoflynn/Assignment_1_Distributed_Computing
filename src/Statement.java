package src;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Statement implements Serializable {
    private int AccNum;
    private String accName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Transaction> transactions;

    public Statement(int accNum, String accName, LocalDateTime startDate, LocalDateTime endDate, List<Transaction> transactions){
        this.AccNum = accNum;
        this.accName = accName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.transactions = transactions;
    }

    public int getAccNum() {
        return this.AccNum;
    }

    public void setAccNum(int AccNum) {
        this.AccNum = AccNum;
    }

    public String getAccName() {
        return this.accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
