package App.model;


public class Guest {
    private long Id;
    private User UserId;
    private Partner PartnerId;
    private boolean Status;
    
    public Guest(){
        
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public User getUserId() {
        return UserId;
    }

    public void setUserId(User UserId) {
        this.UserId = UserId;
    }

    public Partner getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(Partner PartnerId) {
        this.PartnerId = PartnerId;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }
    
    
}
