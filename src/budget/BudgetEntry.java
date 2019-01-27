package budget;

import java.math.BigDecimal;
import java.util.Date;

public class BudgetEntry {
    private BigDecimal amount;
    private String entryName;
    private Date entryDate;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    @Override
    public String toString() {
        return "BudgetEntry{" +
                "amount=" + amount +
                ", entryName='" + entryName + '\'' +
                ", entryDate=" + entryDate +
                '}';
    }
}


