package App.service.interfaces;

import App.dto.InvoiceDto;
import java.util.List;

/**
 *
 * @author Matt
 */
public interface InvoiceService {

    public List<InvoiceDto> findInvoicesByGuestId(long guestId);
    
}
