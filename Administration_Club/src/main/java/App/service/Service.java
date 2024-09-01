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
import java.util.List;

public class Service implements AdminService, LoginService, PartnerService {

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
            throw new Exception("User not registered");
        }
        if (!userDto.getPassword().equals(validateDto.getPassword())) {
            throw new Exception("Incorrect username or password");
        }
        userDto.setRol(validateDto.getRol());
        user = validateDto;
    }

    @Override
    public void logout() {
        user = null;
        System.out.println("Session has been closed");
    }

    private void createUser(UserDto userDto) throws Exception {
        this.createPerson(userDto.getPersonId());
        PersonDto personDto = personDao.findByDocument(userDto.getPersonId());
        userDto.setPersonId(personDto);
        if (this.userDao.existsByUserName(userDto)) {
            this.personDao.deletePerson(userDto.getPersonId());
            throw new Exception("A user with that username already exists");
        }
        try {
            this.userDao.createUser(userDto);
        } catch (SQLException e) {
            this.personDao.deletePerson(userDto.getPersonId());
        }
    }

    private void createPerson(PersonDto personDto) throws Exception {
        if (this.personDao.existsByDocument(personDto)) {
            throw new Exception("A person with that document already exists");
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
        // Create the user associated with the guest
        this.createUser(guestDto.getUserId());
        // Get and assign the partner associated with the guest using the partner's ID
        guestDto.setPartnerId(this.partnerDao.findById(guestDto.getPartnerId().getId()));

        // Verify if the associated partner exists in the database
        if (!this.partnerDao.existsById(guestDto.getPartnerId().getId())) {
            throw new Exception("The associated partner does not exist.");
        }
        // Try to create the guest
        try {
            this.guestDao.createGuest(guestDto);
        } catch (SQLException e) {
            // If an error occurs, propagate the exception
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
