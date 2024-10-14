package App.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private long id;
    private String userName;
    private String password;
    private String rol;
    private PersonDto personId;    
}
