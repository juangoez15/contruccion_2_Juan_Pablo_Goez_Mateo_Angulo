package App;

import App.config.MYSQLConnection;
import App.controller.AdminController;
import App.controller.ControllerInterface;
import App.controller.LoginController;
import App.controller.PartnerController;

import javax.swing.*;

public class AdministrationClub {

    private static final String[] OPTIONS_MENU_PRINCIPAL = {"Manage Partners", "Manage Guests", "Billing", "Exit"};
    private static final String WELCOME_MESSAGE = "Welcome to La Realeza Club";
    private static final String ERROR_MESSAGE = "Error: ";

    public static void main(String[] args) {
        try {
            showWelcomeMessage();
            establishDBConnection();
            LoginController loginController = new LoginController();

            String role = null; // Implement the login method that returns the user's role
            loginController.login();
            showErrorMessage("Login failed. Exiting the system...");
            /*if (role == null) {
                return;
            }

            boolean running = true;
            while (running) {
                int choice = showMainMenu();
                switch (choice) {
                    case 0 -> {
                        if (role.equals("Administrator") || role.equals("Partner")) {
                            managePartner(role);
                        } else {
                            showErrorMessage("Access denied.");
                        }
                    }
                    case 1 -> {
                        if (role.equals("Administrator") || role.equals("Partner")) {
                            manageGuest(role);
                        } else {
                            showErrorMessage("Access denied.");
                        }
                    }
                    case 2 -> {
                        if (role.equals("Administrator")) {
                            billing();
                        } else {
                            showErrorMessage("Access denied.");
                        }
                    }
                    case 3 -> {
                        running = false;
                        showExitMessage();
                    }
                    default ->
                        showErrorMessage("Invalid option. Please try again.");
                }
            }*/
        } catch (Exception e) {
            showErrorMessage(ERROR_MESSAGE + e.getMessage());
        }
    }

    private static void showWelcomeMessage() {
        JOptionPane.showMessageDialog(null, WELCOME_MESSAGE);
    }

    private static void establishDBConnection() {
        MYSQLConnection.getConnection();
    }

    private static int showMainMenu() {
        return JOptionPane.showOptionDialog(null,
                "Select an option:",
                "Main Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                OPTIONS_MENU_PRINCIPAL,
                OPTIONS_MENU_PRINCIPAL[0]);
    }

    private static void billing() {
        AdminController adminController = new AdminController();
        adminController.facturacion();
    }

    private static void showExitMessage() {
        JOptionPane.showMessageDialog(null, "Exiting the system...");
    }

    private static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
