package App.dto;


public class GuestDto {
    private long Id;
    private UserDto UserId;
    private PartnerDto PartnerId;
    private boolean Status;
    
    public GuestDto(){
        
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public UserDto getUserId() {
        return UserId;
    }

    public void setUserId(UserDto UserId) {
        this.UserId = UserId;
    }

    public PartnerDto getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(PartnerDto PartnerId) {
        this.PartnerId = PartnerId;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public PersonDto getPersonId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
