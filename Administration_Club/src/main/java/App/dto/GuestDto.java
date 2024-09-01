package App.dto;

public class GuestDto {

    private long id;
    private UserDto userId;
    private PartnerDto partnerId;
    private String status;
    // Este es el campo que necesitas

    // Constructor
    public GuestDto() {
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDto getUserId() {
        return userId;
    }

    public void setUserId(UserDto userId) {
        this.userId = userId;
    }

    public PartnerDto getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(PartnerDto partnerId) {
        this.partnerId = partnerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
