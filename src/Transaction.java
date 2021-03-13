package src;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Transaction implements Serializable 
{
    private BigDecimal amount;
    private Date date;
    private String description;

    public Transaction(BigDecimal amount, Date date, String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}