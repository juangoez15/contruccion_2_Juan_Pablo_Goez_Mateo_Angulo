/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.controller;

import App.controller.validator.PersonValidator;
import App.controller.validator.UserValidator;
import App.service.Service;
import App.service.interfaces.AdminService;
import javax.swing.JOptionPane;

class GuestController implements ControllerInterface {
    private PersonValidator personValidator;
    private UserValidator userValidator;
    private AdminService service;// Servicio para gestionar socios

    public GuestController() {
       this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.service = new Service();
    }

    public void session() throws Exception {
        boolean sessionActive = true;
        while (sessionActive) {
            String[] options = {"1. Consumo\n", "2. Convertir en socio\n", "3.Salir\n"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Seleccione una opción:",
                    "Menú Invitado",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0:
                    consumo();
                    break;
                case 1:
                    convertGuestToPartner();
                    break;
                case 2:
                    sessionActive = false;
                    JOptionPane.showMessageDialog(null, "Saliendo de la sesión de invitado...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private void consumo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void convertGuestToPartner() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
