package App.controller;

import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.dto.GuestDto;
import App.service.PartnerService;
import App.dto.PartnerDto;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.service.Service;
import App.service.interfaces.AdminService;
import javax.swing.JOptionPane;

public class PartnerController implements ControllerInterface {

    private PartnerService partnerService;

    private PersonValidator personValidator;
    private UserValidator userValidator;
    private AdminService service;// Servicio para gestionar socios

    public PartnerController() {
       this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.service = new Service();
    }

    @Override
    public void session() throws Exception {
        boolean sessionActive = true;
        while (sessionActive) {
            String[] options = {"1. Crear invitado\n", "2. Activar invitado\n", "3. Desactivar invitado\n", "4. Incrementar fondos\n", "5. Hacer consumos\n", "6. Solicitar baja\n", "7. Salir\n"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Seleccione una opción:",
                    "Menú Socio",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0:
                    createGuest();
                    break;
                case 1:
                    changeSubscription();
                    break;
                case 2:
                    viewInvoices();
                    break;
                case 3:
                    manageFunds();
                    break;
                case 4:
                    changeSubscription();
                    break;
                case 5:
                    viewInvoices();
                    break;
                case 6:
                    sessionActive = false;
                    JOptionPane.showMessageDialog(null, "Saliendo de la sesión de Socio...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }
private void createGuest() throws Exception {
    String name = JOptionPane.showInputDialog(null, "Ingrese el nombre del invitado:");
    personValidator.validName(name);
    // Solicitar el ID del invitado
    String idInput = JOptionPane.showInputDialog(null, "Ingrese el ID del invitado:");
    long id = Long.parseLong(idInput);  // Agrega validación si es necesario
    // Solicitar el ID del socio asociado al invitado
    String partnerIdInput = JOptionPane.showInputDialog(null, "Ingrese el ID del socio asociado:");
    long partnerId = Long.parseLong(partnerIdInput);  // Agrega validación si es necesario
    // Crear el DTO de Guest (invitado)
    GuestDto guestDto = new GuestDto();
  //  guestDto.setId(id);
    guestDto.setName(name);
    guestDto.setId(partnerId);
    // Llamar al servicio para crear el invitado
    service.createGuest(guestDto);

    // Mostrar mensaje de éxito
    JOptionPane.showMessageDialog(null, "¡Se ha creado el invitado exitosamente!");
}


    private void manageFunds() {
        try {
            // Asume que tienes un método en SocioService para gestionar fondos
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a agregar:"));
            PartnerDto Partner = getLoggedPartner(); // Método para obtener el socio actualmente logueado

            // Validar fondos y realizar operación
            partnerService.addFunds((int) Partner.getId(), amount);

            JOptionPane.showMessageDialog(null, "Fondos actualizados exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Monto inválido. Intente de nuevo.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al gestionar fondos: " + e.getMessage());
        }
    }

    private void changeSubscription() {
        try {
            // Asume que tienes un método en SocioService para cambiar suscripción
            String newtype = JOptionPane.showInputDialog("Ingrese el tipo de suscripción (Regular o VIP):");
            PartnerDto Partner = getLoggedPartner(); // Método para obtener el socio actualmente logueado

            // Cambiar suscripción
            partnerService.changeSubscription(Partner.getId(), newtype);

            JOptionPane.showMessageDialog(null, "Suscripción cambiada exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cambiar la suscripción: " + e.getMessage());
        }
    }

    private void viewInvoices() {
        try {
            // Asume que tienes un método en SocioService para obtener facturas
            PartnerDto Partner = getLoggedPartner(); // Método para obtener el socio actualmente logueado
            String invoices = partnerService.getInvoices(Partner.getId());

            JOptionPane.showMessageDialog(null, "Historial de Facturas:\n" + invoices);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ver facturas: " + e.getMessage());
        }
    }

    private PartnerDto getLoggedPartner() {
        // Implementar método para obtener el socio actualmente logueado
        // Aquí deberías acceder a la sesión o contexto para obtener la información del socio
        return new PartnerDto(); // Placeholder, reemplaza con la implementación real
    }

}
