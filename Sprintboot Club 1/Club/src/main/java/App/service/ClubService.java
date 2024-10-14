package App.service;

import App.dao.interfaces.DetalinvoiceDao;
import App.dao.interfaces.GuestDao;
import java.sql.SQLException;
import App.dao.interfaces.InvoiceDao;
import App.dao.interfaces.PartnerDao;
import App.dao.interfaces.PersonDao;
import App.dao.interfaces.UserDao;
import App.dto.DetalinvoiceDto;
import App.dto.GuestDto;
import App.dto.InvoiceDto;
import App.dto.PartnerDto;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.service.interfaces.AdminService;
import App.service.interfaces.LoginService;
import App.service.interfaces.PartnerService;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service

public class ClubService implements AdminService, LoginService, PartnerService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PartnerDao partnerDao;
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private GuestDao guestDao;
    @Autowired
    private DetalinvoiceDao detalinvoiceDao;

    public static UserDto user;

    @Override
    public void login(UserDto userDto) throws Exception {
        UserDto validateDto = userDao.findByUserName(userDto);
        if (validateDto == null) {
            throw new Exception("no existe usuario registrado");
        }
        if (!userDto.getPassword().equals(validateDto.getPassword())) {
            throw new Exception("usuario o contraseña incorrecto");
        }
        userDto.setRol(validateDto.getRol());
        user = validateDto;
    }

    @Override
    public void logout() {
        user = null;
        System.out.println("se ha cerrado sesion");
    }

    private void createUser(UserDto userDto) throws Exception {
        this.createPerson(userDto.getPersonId());
        if (this.userDao.existsByUserName(userDto)) {
            this.personDao.deletePerson(userDto.getPersonId());
            throw new Exception("ya existe un usuario con ese user name");
        }
        try {
            this.userDao.createUser(userDto);
        } catch (SQLException e) {
            this.personDao.deletePerson(userDto.getPersonId());
        }
    }

    private void createPerson(PersonDto personDto) throws Exception {
        if (this.personDao.existsByDocument(personDto)) {
            throw new Exception("ya existe una persona con ese documento");
        }
        this.personDao.createPerson(personDto);

    }

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {

        UserDto userDto = partnerDto.getUserId();
        if (userDto == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo en partnerDto");
        }
        this.createUser(userDto);
        this.partnerDao.createPartner(partnerDto);
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        UserDto userDto = guestDto.getUserId();
        this.createUser(userDto);
        PartnerDto partnerDto = partnerDao.findByUserId(user);
        guestDto.setPartnerId(partnerDto);
        this.guestDao.createPerson(guestDto);
    }

    @Override
    public List<InvoiceDto> getPartnerInvoices(long partnerId) {
        try {
            // Obtener todas las facturas asociadas al socio por su ID
            return invoiceDao.findInvoicesByPartnerId(partnerId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retornar una lista vacía en caso de error
        }
    }

    @Override
    public List<PartnerDto> getVipCandidates() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InvoiceDto> getGuestInvoices(long guestId) {
        List<InvoiceDto> guestInvoices = new ArrayList<>();

        try {
            // Verificamos si el invitado existe
            GuestDto guest = guestDao.findById(guestId);
            if (guest == null) {
                throw new Exception("El invitado con ID " + guestId + " no existe.");
            }

            // Buscamos las facturas asociadas a este invitado
            guestInvoices = invoiceDao.findByGuestId(guestId);

            // Si no se encuentran facturas
            if (guestInvoices.isEmpty()) {
                System.out.println("No se encontraron facturas para este invitado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return guestInvoices;
    }

    @Override
    public void approveVipPromotion(List<PartnerDto> candidates) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InvoiceDto> getClubInvoices() {
        try {
            return invoiceDao.findAllInvoices();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void createInvoice(InvoiceDto invoiceDto, List<DetalinvoiceDto> detallesFactura) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void changeSubscription(long id, String newType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInvoices(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InvoiceDto> getInvoiceHistory(long partnerId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DetalinvoiceDto> getInvoiceDetails(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
