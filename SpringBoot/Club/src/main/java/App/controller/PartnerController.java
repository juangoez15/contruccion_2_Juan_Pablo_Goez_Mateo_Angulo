package App.controller;

import App.dto.PartnerDto;
import App.service.PartnerService; // Asegúrate de tener una interfaz para los servicios de socio
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
    private PartnerService PartnerService; // Servicio para gestionar socios

    @Override
    public void session() {
        boolean sessionActive = true;
        Scanner scanner = Utils.getReader();

        while (sessionActive) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Gestionar Fondos");
            System.out.println("2. Cambiar Suscripción");
            System.out.println("3. Ver Facturas");
            System.out.println("4. Salir");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Ingrese un número.");
                continue;
            }

            switch (choice) {
                case 1:
                    manageFunds(scanner);
                    break;
                case 2:
                    changeSubscription(scanner);
                    break;
                case 3:
                    viewInvoices(); // No requiere entrada adicional
                    break;
                case 4:
                    sessionActive = false;
                    System.out.println("Saliendo de la sesión de Socio...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
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
            PartnerService.changeSubscription(partner.getId(), newType);

            System.out.println("Suscripción cambiada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al cambiar la suscripción: " + e.getMessage());
        }
    }

    private void viewInvoices() {
        try {
            PartnerDto partner = getLoggedPartner();
            String invoices = PartnerService.getInvoices(partner.getId());

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
