package App.controller;

import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.dto.InvoiceDto;
import App.dto.PartnerDto;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.service.Service;
import App.service.interfaces.AdminService;
import javax.swing.JOptionPane;
import java.util.List;

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
            + "5. Aprobar promoción.\n"
            + "6. Cerrar sesión.";

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
            case "2": {
                this.viewClubInvoices();
                return true;
            }
            case "3": {
                this.viewPartnerInvoices();
                return true;
            }
            case "4": {
                this.viewGuestInvoices();
                return true;
            }
            case "5": {
                this.approvePromotion();
                return true;
            }
            case "6": {
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
        long identificationCard = personValidator.validDocument(idCardInput);

        // Solicitar el nombre de usuario del socio
        String username = JOptionPane.showInputDialog(null, "Ingrese el nombre de usuario del socio:");
        userValidator.validUsername(username);

        // Solicitar la contraseña del socio
        String password = JOptionPane.showInputDialog(null, "Ingrese la contraseña del socio:");
        userValidator.validPassword(password);

        // Solicitar el número de celular del socio
        String cellphoneInput = JOptionPane.showInputDialog(null, "Ingrese el número de celular del socio:");
        long cellphone = Long.parseLong(cellphoneInput);  // Agrega validación si es necesario

        // Crear los DTOs (Data Transfer Objects)
        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setIdentification_Card(identificationCard);
        personDto.setCelphone(3501235896L);

        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setRol("Socio");

        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto);

        // Llamar al servicio para crear el socio
        service.createPartner(partnerDto);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(null, "¡Se ha creado el socio exitosamente!");

    
    }

    private void viewClubInvoices() {
        // Obtener todas las facturas del club
        List<InvoiceDto> invoices = service.getClubInvoices();

        // Mostrar las facturas al administrador
        StringBuilder message = new StringBuilder("Facturas del Club:\n\n");
        for (InvoiceDto invoice : invoices) {
            message.append(invoice.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), "Facturas del Club", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewPartnerInvoices() throws Exception {
        // Solicitar el ID del socio
        String partnerIdInput = JOptionPane.showInputDialog(null, "Ingrese el ID del socio:");
        long UserId = Long.parseLong(partnerIdInput);

        // Obtener facturas del socio
        List<InvoiceDto> invoices = service.getPartnerInvoices(UserId);

        // Mostrar las facturas al administrador
        StringBuilder message = new StringBuilder("Facturas del Socio:\n\n");
        for (InvoiceDto invoice : invoices) {
            message.append(invoice.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), "Facturas del Socio", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewGuestInvoices() throws Exception {
        // Solicitar el ID del invitado
        String guestIdInput = JOptionPane.showInputDialog(null, "Ingrese el ID del invitado:");
        long guestId = Long.parseLong(guestIdInput);

        // Obtener facturas del invitado
        List<InvoiceDto> invoices = service.getGuestInvoices(guestId);

        // Mostrar las facturas al administrador
        StringBuilder message = new StringBuilder("Facturas del Invitado:\n\n");
        for (InvoiceDto invoice : invoices) {
            message.append(invoice.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), "Facturas del Invitado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void approvePromotion() throws Exception {
        // Generar lista de candidatos a VIP
        List<PartnerDto> candidates = service.getVipCandidates();

        if (candidates.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay candidatos disponibles para promoción a VIP.", "Promoción a VIP", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder message = new StringBuilder("Candidatos a VIP:\n\n");
        for (PartnerDto candidate : candidates) {
            message.append(candidate.toString()).append("\n");
        }

        // Mostrar los candidatos al administrador y permitir la aprobación
        int approve = JOptionPane.showConfirmDialog(null, message.toString() + "\n¿Desea aprobar la promoción?", "Aprobar Promoción", JOptionPane.YES_NO_OPTION);
        if (approve == JOptionPane.YES_OPTION) {
            service.approveVipPromotion(candidates);
            JOptionPane.showMessageDialog(null, "Promoción a VIP aprobada.", "Promoción a VIP", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Promoción a VIP cancelada.", "Promoción a VIP", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void login() {
        // Implementar la lógica de login o dejarlo como método vacío para evitar la excepción
    }

    public void facturacion() {
        // Implementar la lógica de facturación o dejarlo como método vacío para evitar la excepción
    }
}
