package App.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class DetalinvoiceDto {
    private long id;
    private InvoiceDto invoiceId;
    private int item;
    private String description;
    private double amount;
    
   
    
}
