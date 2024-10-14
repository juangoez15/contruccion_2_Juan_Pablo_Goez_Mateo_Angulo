/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package App.dao.interfaces;

import App.dto.InvoiceDto;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface InvoiceDao {

    public List<InvoiceDto> findAllInvoices();

    public List<InvoiceDto> findInvoicesByPartnerId(long partnerId);

    public List<InvoiceDto> findByGuestId(long guestId);
    
    
}
