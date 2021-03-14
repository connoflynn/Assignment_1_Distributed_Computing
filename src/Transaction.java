package src;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction implements Serializable 
{
    private BigDecimal amount;
    private LocalDateTime date;
    private String description;

    public Transaction(BigDecimal amount, LocalDateTime date, String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}