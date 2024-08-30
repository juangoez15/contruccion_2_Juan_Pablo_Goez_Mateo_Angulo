package App.service;

import App.dao.interfaces.DetalinvoiceDao;
import App.dao.interfaces.GuestDao;
import java.sql.Date;
import java.sql.SQLException;
import App.dao.interfaces.PersonDaoImplementation;
import App.dao.interfaces.UserDaoImplementation;
import App.dao.interfaces.InvoiceDao;
import App.dao.interfaces.PartnerDao;
import App.dao.interfaces.PartnerDaoImplementation;
import App.dao.interfaces.PersonDao;
import App.dao.interfaces.UserDao;
import App.dto.GuestDto;
import App.dto.InvoiceDto;
import App.dto.PartnerDto;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.service.interfaces.AdminService;
import App.service.interfaces.LoginService;
import App.service.interfaces.LoginService;
import java.util.List;

public class Service implements AdminService, LoginService, PartnerService{

    private UserDao userDao;
    private PersonDao personDao;
    private PartnerDao partnerDao;
    private InvoiceDao invoiceDao;
    private GuestDao guestDao;
    private DetalinvoiceDao detalinvoiceDao;

    public static UserDto user;

    public Service() {
        this.userDao = new UserDaoImplementation();
        this.personDao = new PersonDaoImplementation();
        this.partnerDao = new PartnerDaoImplementation();
    }

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
        PersonDto personDto = personDao.findByDocument(userDto.getPersonId());
        userDto.setPersonId(personDto);
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
        this.createUser(partnerDto.getUserId());
        partnerDto.setUserId(this.userDao.findByUserName(partnerDto.getUserId()));
        try {
            this.partnerDao.createPartner(partnerDto);
        } catch (SQLException e) {
            this.personDao.deletePerson(partnerDto.getUserId().getPersonId());
            throw e;
        }
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        // Crear el usuario asociado al invitado
        this.createUser(guestDto.getUserId());
        // Obtener y asignar el socio asociado al invitado usando el ID del socio
        guestDto.setPartnerId(this.partnerDao.findById(guestDto.getPartnerId().getId()));

        // Verificar si el socio asociado existe en la base de datos
        if (!this.partnerDao.existsById(guestDto.getPartnerId().getId())) {
            throw new Exception("El socio asociado no existe.");
        }
        // Intentar crear el invitado
        try {
            this.guestDao.createGuest(guestDto);
        } catch (SQLException e) {
            // Si ocurre un error, propagar la excepción
            throw e;
        }
    }

    @Override
    public List<InvoiceDto> getPartnerInvoices(long partnerId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PartnerDto> getVipCandidates() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InvoiceDto> getGuestInvoices(long guestId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void approveVipPromotion(List<PartnerDto> candidates) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InvoiceDto> getClubInvoices() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void changeSubscription(long id, String newtype) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInvoices(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addFunds(int i, double amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
