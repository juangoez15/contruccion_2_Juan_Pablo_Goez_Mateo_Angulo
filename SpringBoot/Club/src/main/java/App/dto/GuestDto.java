package App.dto;
 
public class GuestDto {
    private long id;
    private String name;
    private long partnerId; // Este es el campo que necesitas
 
    // Constructor
    public GuestDto() {}
 
    // Getters y Setters
    public long getId() {
        return id;
    }
 
    public void setId(long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public long getPartnerId() {
        return partnerId;
    }
 
    public void setPartnerId(long partnerId) {
        this.partnerId = partnerId;
    }

    public UserDto getUserId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}