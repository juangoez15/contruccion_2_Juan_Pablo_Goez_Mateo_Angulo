package App.model;

import java.util.Date;

public class Invoice {
    private long Id;
    private Date CreacionDate;
    private Partner PartnerId;
    private User UserId;
    private double TotalAmount;
    private boolean Status;
    
    public Invoice(){
        
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public Date getCreacionDate() {
        return CreacionDate;
    }

    public void setCreacionDate(Date CreacionDate) {
        this.CreacionDate = CreacionDate;
    }

    public Partner getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(Partner PartnerId) {
        this.PartnerId = PartnerId;
    }

    public User getUserId() {
        return UserId;
    }

    public void setUserId(User UserId) {
        this.UserId = UserId;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }
    
    
}
