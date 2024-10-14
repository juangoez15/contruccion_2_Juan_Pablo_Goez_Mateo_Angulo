package App.dto;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDto {
    private long Id;
    private Timestamp creacionDate;
    private PartnerDto partnerId;
    private UserDto userId;
    private double totalAmount;
    private boolean status;
   
        
}
