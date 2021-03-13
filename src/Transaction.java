package src;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Transaction extends Serializable 
{
    // Needs some accessor methods to return information about the transaction
    public BigDecimal getAmount();
    public Date getDate();
    public String description;
}