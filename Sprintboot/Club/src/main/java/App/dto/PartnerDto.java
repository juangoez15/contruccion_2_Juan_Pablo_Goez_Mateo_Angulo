package App.dto;

import java.util.Date;

public class PartnerDto {

    private long Id;
    private double amaunt;
    private boolean type;
    private Date creationdate;
    private UserDto UserId;
    private PersonDto personId;

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

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public UserDto getUserId() {
        return UserId;
    }

    public void setUserId(UserDto UserId) {
        this.UserId = UserId;
    }

    public PersonDto getPersonId() {
        return personId;
    }

    public void setpersonId(PersonDto personId) {
        this.personId = personId;
    }

}
