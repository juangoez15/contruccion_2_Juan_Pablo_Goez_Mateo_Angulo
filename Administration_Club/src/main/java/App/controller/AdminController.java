package App.controller;

import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.service.Service;
import App.service.interfaces.AdminService;


public class AdminController {
    	private PersonValidator personValidator;
	private UserValidator userValidator;
	private AdminService service;
	private static final String MENU = "ingrese la opcion que desea \n 1.Registro de socio. \n 2. Historial de facturas \n 3. Promoción a VIP \n";

	public AdminController() {
		this.personValidator = new PersonValidator();
		this.userValidator = new UserValidator();
		this.service = new Service();
	}

	
	public void session() throws Exception {
		boolean session = true;
		while (session) {
			session = menu();
		}

	}

	private boolean menu() {
		try {
			System.out.println("Bienvenido " + Service.user.getUsername());
			System.out.print(MENU);
			String option = Utils.getReader().nextLine();
			return options(option);

		} catch (

		Exception e) {
			System.out.println(e.getMessage());
			return true;
		}
	}

	private boolean options(String option) throws Exception{
		switch (option) {
		case "1": {
			this.createPartner();
			return true;
		}
		/*case "2": {
			this.createSeller();
			return true;
		}*/
		case "3": {
			System.out.println("se ha cerrado sesion");
			return false;
		}
		default: {
			System.out.println("ingrese una opcion valida");
			return true;
		}
		}
	}

	private void createPartner() throws Exception {
		System.out.println("ingrese el nombre del socio" );
		String name = Utils.getReader().nextLine();
		personValidator.validName(name);
		System.out.println("ingrese la cedula del socio");
		long Identification_Card= personValidator.validDocument(Utils.getReader().nextLine());
		System.out.println("ingrese el nombre de usuario del socio");
		String username = Utils.getReader().nextLine();
		userValidator.validUsername(username);
		System.out.println("ingrese la contraseña del socio");
		String password = Utils.getReader().nextLine();
		userValidator.validPassword(password);
		PersonDto personDto = new PersonDto();
		personDto.setName(name);
		personDto.setIdentification_Card(Identification_Card);
		UserDto userDto = new UserDto();
		userDto.setPersonId(personDto);
		userDto.setUsername(username);
		userDto.setPassword(password);
		userDto.setRol("Socio");
		System.out.println("se ha creado el usuario exitosamente");
	}

	/*private void createSeller() throws Exception {
		System.out.println("ingrese el nombre del vendedor");
		String name = Utils.getReader().nextLine();
		personValidator.validName(name);
		System.out.println("ingrese la cedula del vendedor");
		long Identification_Card = personValidator.validDocument(Utils.getReader().nextLine());
		System.out.println("ingrese la edad del vendedor");
		int age = personValidator.validAge(Utils.getReader().nextLine());
		System.out.println("ingrese el nombre de usuario del vendedor");
		String username = Utils.getReader().nextLine();
		userValidator.validUsername(username);
		System.out.println("ingrese la contraseña del vendedor");
		String password = Utils.getReader().nextLine();
		userValidator.validPassword(password);
		PersonDto personDto = new PersonDto();
		personDto.setName(name);
		personDto.setIdentification_Card(Identification_Card);
		UserDto userDto = new UserDto();
		userDto.setPersonId(personDto);
		userDto.setUsername(username);
		userDto.setPassword(password);
		userDto.setRol("seller");
		System.out.println("se ha creado el usuario exitosamente");
	}*/

}
