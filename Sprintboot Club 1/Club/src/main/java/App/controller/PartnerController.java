package App.controller;

import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.dto.GuestDto;
import App.dto.PartnerDto;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.service.interfaces.PartnerService;
import java.sql.Timestamp;
import java.util.Scanner;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Setter
@Getter
@NoArgsConstructor
public class PartnerController implements ControllerInterface {

    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private PartnerService service; // Servicio para gestionar socios

     private static final String MENU
            = "Ingrese la opción que desea:\n"
            + "1. Crear invitado.\n"
            + "2. manageFunds.\n"
            + "3. changeSubscription.\n"
            + "4. viewInvoices.\n"
            + "5. Salir.\n";




    public void session() throws Exception {
        Scanner scanner = Utils.getReader();
        boolean session = true;
        while (session) {
            session = menu(scanner);
        }
    }

    private boolean menu(Scanner scanner) {
        try {
            System.out.println(MENU);
            String option = scanner.nextLine();

            if (option.isEmpty()) {
                return false;
            }
            return options(option, scanner); // Pass the scanner object
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return true;
        }
    }

    private boolean options(String option, Scanner scanner) throws Exception {
        switch (option) {
            case "1": {
                this.createGuest(scanner);
                return true;
            }
            case "2": {
                this.manageFunds(scanner);
                return true;
            }
            case "3": {
                this.changeSubscription(scanner);
                return true;
            }
            case "4": {
                this.viewInvoices(scanner);
                return true;
            }
            case "5": {
                System.out.println("Se ha cerrado sesión");
                return false;
            }
            default: {
                System.out.println("Ingrese una opción válida");
                return true;
            }
        }
    }

    private void createGuest(Scanner scanner) throws Exception {
        System.out.print("Ingrese el nombre del invitado: ");
        String name = scanner.nextLine();
        personValidator.validName(name);
        System.out.print("Ingrese la cédula del invitado: ");
        String idCardInput = scanner.nextLine();
        long Document = personValidator.validDocument(idCardInput);
        System.out.print("Ingrese el nombre de usuario del invitado: ");
        String username = scanner.nextLine();
        userValidator.validUsername(username);
        System.out.print("Ingrese la contraseña del invitado: ");
        String password = scanner.nextLine();
        userValidator.validPassword(password);
        
        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setDocument(Document);
        personDto.setCelphone(3501235896L);
        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto);
        userDto.setUserName(username);
        userDto.setPassword(password);
        userDto.setRol("Invitado");
        
        GuestDto guestDto = new GuestDto();
        guestDto.setUserId(userDto);
        guestDto.setStatus(false);
        this.service.createGuest(guestDto);
        System.out.println("¡Se ha creado el invitado exitosamente!");
    }
    
    private void manageFunds(Scanner scanner) {
        try {
            System.out.print("Ingrese el monto a agregar: ");
            double amount = scanner.nextDouble();
            // Resto del código sin cambios
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido. Ingrese un número.");
        } catch (Exception e) {
            // ...
        }
    }

    private void changeSubscription(Scanner scanner) {
        try {
            System.out.print("Ingrese el nuevo tipo de suscripción (Regular o VIP): ");
            String newType = scanner.nextLine();

            PartnerDto partner = getLoggedPartner();
            service.changeSubscription(partner.getId(), newType);

            System.out.println("Suscripción cambiada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al cambiar la suscripción: " + e.getMessage());
        }
    }

    private void viewInvoices(Scanner scanner) {
        try {
            PartnerDto partner = getLoggedPartner();
            String invoices = service.getInvoices(partner.getId());

            System.out.println("Historial de Facturas:\n" + invoices);
        } catch (Exception e) {
            System.out.println("Error al ver facturas: " + e.getMessage());
        }
    }

    private PartnerDto getLoggedPartner() {
        // Simulación simplificada:
        // En un entorno real, obtendrías esta información de la sesión o contexto del usuario
        //return partner;
        return null;
        // Simulación simplificada:
        // En un entorno real, obtendrías esta información de la sesión o contexto del usuario
        //return partner;
    }

    public void login() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
