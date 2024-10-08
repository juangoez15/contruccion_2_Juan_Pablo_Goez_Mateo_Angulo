package App.service.interfaces;


import App.dto.DetalinvoiceDto;
import App.dto.InvoiceDto;
import App.dto.PartnerDto;
import App.dto.UserDto;
import java.util.List;

public interface AdminService {
public void createPartner(PartnerDto partnerDto) throws Exception;

    public List<InvoiceDto> getPartnerInvoices(long partnerId);

    public List<PartnerDto> getVipCandidates();

    public List<InvoiceDto> getGuestInvoices(long guestId);

    public void approveVipPromotion(List<PartnerDto> candidates);

    public List<InvoiceDto> getClubInvoices();

    public void createInvoice(InvoiceDto invoiceDto, List<DetalinvoiceDto> detallesFactura);



}
