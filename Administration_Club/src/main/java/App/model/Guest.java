package App.model;

import App.dto.UserDto;


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

    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setUSerId(UserDto userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
