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
            = "Select the option you want:\n"
            + "1. Create partner.\n"
            + "2. View club invoice.\n"
            + "3. View partner invoice.\n"
            + "4. View guest invoice.\n"
            + "5. Approve promotion.\n"
            + "6. Log out.";

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
            JOptionPane.showMessageDialog(null, "Welcome " + Service.user.getUsername(), "Welcome", JOptionPane.INFORMATION_MESSAGE);

            String option = JOptionPane.showInputDialog(null, MENU, "Options Menu", JOptionPane.QUESTION_MESSAGE);

            if (option == null) {
                return false;
            }
            return options(option);
        } catch (Exception e) {
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
                JOptionPane.showMessageDialog(null, "Session has been closed", "Log Out", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            default: {
                JOptionPane.showMessageDialog(null, "Please enter a valid option", "Invalid Option", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
    }

    private void createPartner() throws Exception {
        String name = JOptionPane.showInputDialog(null, "Enter partner's name:");
        personValidator.validName(name);

        String idCardInput = JOptionPane.showInputDialog(null, "Enter partner's ID card number:");
        long identificationCard = personValidator.validDocument(idCardInput);

        String username = JOptionPane.showInputDialog(null, "Enter partner's username:");
        userValidator.validUsername(username);

        String password = JOptionPane.showInputDialog(null, "Enter partner's password:");
        userValidator.validPassword(password);

        String cellphoneInput = JOptionPane.showInputDialog(null, "Enter partner's cellphone number:");
        long cellphone = Long.parseLong(cellphoneInput);

        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setIdentification_Card(identificationCard);
        personDto.setCelphone(cellphone);

        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto);
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setRol("Partner");

        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto);

        service.createPartner(partnerDto);

        JOptionPane.showMessageDialog(null, "Partner has been successfully created!");
    }

    private void viewClubInvoices() {
        List<InvoiceDto> invoices = service.getClubInvoices();

        StringBuilder message = new StringBuilder("Club Invoices:\n\n");
        for (InvoiceDto invoice : invoices) {
            message.append(invoice.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), "Club Invoices", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewPartnerInvoices() throws Exception {
        String partnerIdInput = JOptionPane.showInputDialog(null, "Enter partner's ID:");
        long UserId = Long.parseLong(partnerIdInput);

        List<InvoiceDto> invoices = service.getPartnerInvoices(UserId);

        StringBuilder message = new StringBuilder("Partner Invoices:\n\n");
        for (InvoiceDto invoice : invoices) {
            message.append(invoice.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), "Partner Invoices", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewGuestInvoices() throws Exception {
        String guestIdInput = JOptionPane.showInputDialog(null, "Enter guest's ID:");
        long guestId = Long.parseLong(guestIdInput);

        List<InvoiceDto> invoices = service.getGuestInvoices(guestId);

        StringBuilder message = new StringBuilder("Guest Invoices:\n\n");
        for (InvoiceDto invoice : invoices) {
            message.append(invoice.toString()).append("\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), "Guest Invoices", JOptionPane.INFORMATION_MESSAGE);
    }

    private void approvePromotion() throws Exception {
        List<PartnerDto> candidates = service.getVipCandidates();

        if (candidates.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No candidates available for VIP promotion.", "VIP Promotion", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder message = new StringBuilder("VIP Candidates:\n\n");
        for (PartnerDto candidate : candidates) {
            message.append(candidate.toString()).append("\n");
        }

        int approve = JOptionPane.showConfirmDialog(null, message.toString() + "\nDo you want to approve the promotion?", "Approve Promotion", JOptionPane.YES_NO_OPTION);
        if (approve == JOptionPane.YES_OPTION) {
            service.approveVipPromotion(candidates);
            JOptionPane.showMessageDialog(null, "VIP promotion approved.", "VIP Promotion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "VIP promotion canceled.", "VIP Promotion", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void login() {
        // Implement login logic or leave empty
    }

    public void facturacion() {
        // Implement billing logic or leave empty
    }
}
