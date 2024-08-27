package App;

import App.config.MYSQLConnection;
import App.controller.AdminController;
import App.controller.ControllerInterface;
import App.controller.LoginController;
import App.controller.PartnerController;

import javax.swing.*;

public class AdministrationClub {

    private static final String[] OPTIONS_MENU_PRINCIPAL = {"Gestionar Socios", "Gestionar Invitados", "Facturación", "Salir"};
    private static final String MENSAJE_BIENVENIDA = "Bienvenido al Club La Realeza";
    private static final String MENSAJE_ERROR = "Error: ";

    public static void main(String[] args) {
        try {
            mostrarMensajeBienvenida();
            establecerConexionBD();
            LoginController loginController = new LoginController();

            String rol = null; // Implementa el método de login que devuelva el rol del usuario
            loginController.login();

            if (rol == null) {
                mostrarMensajeError("Inicio de sesión fallido. Saliendo del sistema...");
                return;
            }

            boolean running = true;
            while (running) {
                int choice = mostrarMenuPrincipal();
                switch (choice) {
                    case 0 -> {
                        if (rol.equals("Administrador") || rol.equals("Socio")) {
                            gestionarPartner(rol);
                        } else {
                            mostrarMensajeError("Acceso denegado.");
                        }
                    }
                    case 1 -> {
                        if (rol.equals("Administrador") || rol.equals("Socio")) {
                            gestionarGuest(rol);
                        } else {
                            mostrarMensajeError("Acceso denegado.");
                        }
                    }
                    case 2 -> {
                        if (rol.equals("Administrador")) {
                            facturacion();
                        } else {
                            mostrarMensajeError("Acceso denegado.");
                        }
                    }
                    case 3 -> {
                        running = false;
                        mostrarMensajeSalida();
                    }
                    default ->
                        mostrarMensajeError("Opción no válida. Intente de nuevo.");
                }
            }
        } catch (Exception e) {
            mostrarMensajeError(MENSAJE_ERROR + e.getMessage());
        }
    }

    private static void mostrarMensajeBienvenida() {
        JOptionPane.showMessageDialog(null, MENSAJE_BIENVENIDA);
    }

    private static void establecerConexionBD() {
        MYSQLConnection.getConnection();
    }

    private static int mostrarMenuPrincipal() {
        return JOptionPane.showOptionDialog(null,
                "Seleccione una opción:",
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                OPTIONS_MENU_PRINCIPAL,
                OPTIONS_MENU_PRINCIPAL[0]);
    }

    private static void gestionarPartner(String rol) {
        // Crear un controlador de socios según el rol del usuario
        PartnerController PartnerController = new PartnerController();
        PartnerController.gestionar(rol);
    }

    private static void gestionarGuest(String rol) {
        // Crear un controlador de invitados según el rol del usuario
        GuestController GuestController = new GuestController();
        GuestController.gestionar(rol);
    }

    private static void facturacion() {
        AdminController adminController = new AdminController();
        adminController.facturacion();
    }

    private static void mostrarMensajeSalida() {
        JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
    }

    private static void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
