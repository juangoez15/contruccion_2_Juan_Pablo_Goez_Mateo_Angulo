package App.service;

import App.dao.interfaces.DetalinvoiceDao;
import App.dao.interfaces.GuestDao;
import java.sql.Date;
import java.sql.SQLException;

import App.dao.interfaces.PersonDaoImplementation;
import App.dao.interfaces.UserDaoImplementation;
import App.dao.interfaces.InvoiceDao;
import App.dao.interfaces.PartnerDao;
import App.dao.interfaces.PersonDao;
import App.dao.interfaces.UserDao;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.service.interfaces.AdminService;
import app.service.interfaces.LoginService;

public class Service implements  AdminService, LoginService{
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

}
