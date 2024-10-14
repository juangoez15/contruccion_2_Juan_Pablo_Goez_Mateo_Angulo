package App.dto;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartnerDto {

    private long id;
    private double amaunt;
    private boolean type;
    private Timestamp creationDate;
    private UserDto userId;
    private PersonDto personId;

    public PersonDto getPersonId() {
        return personId;
    }

    public void setpersonId(PersonDto personId) {
        this.personId = personId;
    }

}
