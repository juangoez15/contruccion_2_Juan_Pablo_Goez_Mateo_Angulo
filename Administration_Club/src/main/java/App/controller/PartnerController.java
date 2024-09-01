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
    private AdminService service; // Service to manage partners

    public PartnerController() {
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.service = new Service();
    }

    @Override
    public void session() throws Exception {
        boolean sessionActive = true;
        while (sessionActive) {
            String[] options = {
                "1. Create guest\n",
                "2. Activate guest\n",
                "3. Deactivate guest\n",
                "4. Add funds\n",
                "5. Make purchases\n",
                "6. Request withdrawal\n",
                "7. Exit\n"
            };
            int choice = JOptionPane.showOptionDialog(null,
                    "Select an option:",
                    "Partner Menu",
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
                    JOptionPane.showMessageDialog(null, "Exiting Partner session...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void createGuest() throws Exception {
        // Request guest's name
        String name = JOptionPane.showInputDialog(null, "Enter the guest's name:");
        personValidator.validName(name);

        // Request guest's ID card
        String idCardInput = JOptionPane.showInputDialog(null, "Enter the guest's ID card:");
        long identificationCard = personValidator.validDocument(idCardInput);

        // Request guest's username
        String username = JOptionPane.showInputDialog(null, "Enter the guest's username:");
        userValidator.validUsername(username);

        // Request guest's password
        String password = JOptionPane.showInputDialog(null, "Enter the guest's password:");
        userValidator.validPassword(password);

        // Request guest's cellphone number
        String cellphoneInput = JOptionPane.showInputDialog(null, "Enter the guest's cellphone number:");
        long cellphone = Long.parseLong(cellphoneInput);  // Add validation if necessary

        // Create DTOs (Data Transfer Objects)
        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setIdentification_Card(identificationCard);
        personDto.setCelphone(3501235896L);

        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setRol("Partner");

        GuestDto guestDto = new GuestDto();
        guestDto.setUserId(userDto);

        // Call the service to create the guest
        service.createGuest(guestDto);

        // Show success message
        JOptionPane.showMessageDialog(null, "Guest created successfully!");
    }

    private void manageFunds() {
        try {
            // Assume you have a method in PartnerService to manage funds
            double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter the amount to add:"));
            PartnerDto partner = getLoggedPartner(); // Method to get the currently logged partner

            // Validate funds and perform operation
            partnerService.addFunds((int) partner.getId(), amount);

            JOptionPane.showMessageDialog(null, "Funds updated successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid amount. Please try again.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error managing funds: " + e.getMessage());
        }
    }

    private void changeSubscription() {
        try {
            // Assume you have a method in PartnerService to change subscription
            String newType = JOptionPane.showInputDialog("Enter the subscription type (Regular or VIP):");
            PartnerDto partner = getLoggedPartner(); // Method to get the currently logged partner

            // Change subscription
            partnerService.changeSubscription(partner.getId(), newType);

            JOptionPane.showMessageDialog(null, "Subscription changed successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error changing subscription: " + e.getMessage());
        }
    }

    private void viewInvoices() {
        try {
            // Assume you have a method in PartnerService to get invoices
            PartnerDto partner = getLoggedPartner(); // Method to get the currently logged partner
            String invoices = partnerService.getInvoices(partner.getId());

            JOptionPane.showMessageDialog(null, "Invoice History:\n" + invoices);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error viewing invoices: " + e.getMessage());
        }
    }

    private PartnerDto getLoggedPartner() {
        // Implement method to get the currently logged partner
        // Here you should access the session or context to get the partner's information
        return new PartnerDto(); // Placeholder, replace with the actual implementation
    }
}
