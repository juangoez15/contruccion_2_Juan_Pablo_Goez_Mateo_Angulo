package App.controller;

import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.dto.PartnerDto;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.service.Service;
import App.service.interfaces.AdminService;
import javax.swing.JOptionPane;

public class AdminController implements ControllerInterface {
    
    private PersonValidator personValidator;
    private UserValidator userValidator;
    private AdminService service;
    private static final String MENU
            = "Ingrese la opción que desea:\n"
            + "1. Crear socio.\n"
            + "2. Ver factura club.\n"
            + "3. Ver factura socio.\n"
            + "4. Ver factura invitado.\n"
            + "5. Aprobar promoción.";
    
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
            // Mostrar el nombre de usuario
            JOptionPane.showMessageDialog(null, "Bienvenido " + Service.user.getUsername(), "Bienvenido", JOptionPane.INFORMATION_MESSAGE);

            // Mostrar el menú y solicitar la opción al usuario
            String option = JOptionPane.showInputDialog(null, MENU, "Menú de Opciones", JOptionPane.QUESTION_MESSAGE);

            // Si se cancela el diálogo o se cierra, salir del menú
            if (option == null) {
                return false;
            }
            
            return options(option);
            
        } catch (Exception e) {
            // Mostrar mensaje de error
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
    }
    
    private boolean options(String option) throws Exception {
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
                JOptionPane.showMessageDialog(null, "Se ha cerrado sesión", "Cerrar Sesión", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            default: {
                JOptionPane.showMessageDialog(null, "Ingrese una opción válida", "Opción inválida", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
    }
    
    private void createPartner() throws Exception {
        // Solicitar el nombre del socio
        String name = JOptionPane.showInputDialog(null, "Ingrese el nombre del socio:");
        personValidator.validName(name);

        // Solicitar la cédula del socio
        String idCardInput = JOptionPane.showInputDialog(null, "Ingrese la cédula del socio:");
        long Identification_Card = personValidator.validDocument(idCardInput);

        // Solicitar el nombre de usuario del socio
        String username = JOptionPane.showInputDialog(null, "Ingrese el nombre de usuario del socio:");
        userValidator.validUsername(username);

        // Solicitar la contraseña del socio
        String password = JOptionPane.showInputDialog(null, "Ingrese la contraseña del socio:");
        userValidator.validPassword(password);

        // Crear los DTOs (Data Transfer Objects)
        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setIdentification_Card(Identification_Card);
        personDto.setCelphone(3501235896L);
        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setRol("Socio");
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto);
        service.createPartner(partnerDto);
        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(null, "¡Se ha creado el usuario exitosamente!");
    }
    
    @Override
    public void login() {
        throw new UnsupportedOperationException("Not supported yet.");        
    }
    
    public void facturacion() {
        throw new UnsupportedOperationException("Not supported yet.");        
    }
}
