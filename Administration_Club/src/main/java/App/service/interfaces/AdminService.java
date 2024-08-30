package App.service.interfaces;


import App.dto.GuestDto;
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

    public void createGuest(GuestDto guestDto)throws Exception;



}
