package App.dto;

import App.model.Person;
import java.util.Date;

public class PartnerDto {

    private long Id;
    private double amaunt;
    private boolean type;
    private Date creation_date;
    private UserDto UserId;

    public PartnerDto() {

    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public double getAmaunt() {
        return amaunt;
    }

    public void setAmaunt(double amaunt) {
        this.amaunt = amaunt;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public UserDto getUserId() {
        return UserId;
    }

    public void setUserId(UserDto UserId) {
        this.UserId = UserId;
    }

    public Person getPerson() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
