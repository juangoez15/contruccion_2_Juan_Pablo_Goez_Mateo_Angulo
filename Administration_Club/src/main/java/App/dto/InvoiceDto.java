package App.dto;

import java.util.Date;

public class InvoiceDto {
    private long Id;
    private Date CreacionDate;
    private PartnerDto PartnerId;
    private UserDto UserId;
    private double TotalAmount;
    private boolean Status;
    
    public InvoiceDto(){
        
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

    public PartnerDto getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(PartnerDto PartnerId) {
        this.PartnerId = PartnerId;
    }

    public UserDto getUserId() {
        return UserId;
    }

    public void setUserId(UserDto UserId) {
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
