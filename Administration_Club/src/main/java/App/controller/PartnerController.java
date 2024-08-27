package App.controller;

import App.dto.PartnerDto;
import App.service.PartnerService; // Asegúrate de tener una interfaz para los servicios de socio
import javax.swing.JOptionPane;

public class PartnerController implements ControllerInterface {
    private final PartnerService PartnerService; // Servicio para gestionar socios

    public PartnerController() {
        this.PartnerService = new PartnerService();
    }

    @Override
    public void session() {
        boolean sessionActive = true;
        while (sessionActive) {
            String[] options = {"Gestionar Fondos", "Cambiar Suscripción", "Ver Facturas", "Salir"};
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
                    manageFunds();
                    break;
                case 1:
                    changeSubscription();
                    break;
                case 2:
                    viewInvoices();
                    break;
                case 3:
                    sessionActive = false;
                    JOptionPane.showMessageDialog(null, "Saliendo de la sesión de Socio...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private void manageFunds() {
        try {
            // Asume que tienes un método en SocioService para gestionar fondos
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto a agregar:"));
            PartnerDto Partner = getLoggedPartner(); // Método para obtener el socio actualmente logueado

            // Validar fondos y realizar operación
            PartnerService.addFunds((int) Partner.getId(), amount);

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
            PartnerService.changeSubscription(Partner.getId(), newtype);

            JOptionPane.showMessageDialog(null, "Suscripción cambiada exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cambiar la suscripción: " + e.getMessage());
        }
    }

    private void viewInvoices() {
        try {
            // Asume que tienes un método en SocioService para obtener facturas
            PartnerDto Partner = getLoggedPartner(); // Método para obtener el socio actualmente logueado
            String invoices = PartnerService.getInvoices(Partner.getId());

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

    public void gestionar(String rol) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void login() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
