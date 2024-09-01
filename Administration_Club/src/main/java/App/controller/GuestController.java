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
    private AdminService service; // Service to manage partners

    public GuestController() {
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.service = new Service();
    }

    public void session() throws Exception {
        boolean sessionActive = true;
        while (sessionActive) {
            String[] options = {"1. Consumption\n", "2. Convert to partner\n", "3. Exit\n"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Select an option:",
                    "Guest Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0:
                    consumption();
                    break;
                case 1:
                    convertGuestToPartner();
                    break;
                case 2:
                    sessionActive = false;
                    JOptionPane.showMessageDialog(null, "Exiting guest session...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void consumption() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void convertGuestToPartner() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
