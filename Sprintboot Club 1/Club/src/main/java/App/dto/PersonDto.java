package App.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonDto {
    private long Id;
    private long Document;
    private String Name;
    private long Celphone;    
}
