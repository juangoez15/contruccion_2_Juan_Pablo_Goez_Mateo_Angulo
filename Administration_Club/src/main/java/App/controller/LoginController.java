package App.controller;

import App.controller.AdminController;
import App.controller.ControllerInterface;
import App.controller.Utils;
import java.util.HashMap;
import java.util.Map;
import App.controller.validator.UserValidator;
import App.controller.validator.UserValidator;
import App.dto.UserDto;
import App.service.Service;
import App.service.interfaces.LoginService;
import App.service.interfaces.LoginService;
import javax.swing.JOptionPane;

public class LoginController implements ControllerInterface {

    private UserValidator userValidator;
    private LoginService service;
    private static final String MENU = "ingrese la opcion que desea: \n 1. para iniciar sesion. \n 2. para detener la ejecucion.";
    private Map<String, ControllerInterface> roles;

    public LoginController() {
        this.userValidator = new UserValidator();
        this.service = (LoginService) new Service();
        ControllerInterface adminController = (ControllerInterface) new AdminController();
        this.roles = new HashMap<String, ControllerInterface>();
        roles.put("admin", adminController);
    }

    @Override
    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = menu();
        }
    }

    private boolean menu() {
        try {
            System.out.println(MENU);
            String option = Utils.getReader().nextLine();
            return options(option);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1": {
                this.login();
                return true;
            }
            case "2": {
                System.out.println("se detiene el programa");;
                return false;
            }
            default: {
                System.out.println("ingrese una opcion valida");
                return true;
            }
        }
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void login() throws Exception {
        try {
            // Solicitar el nombre de usuario
            String username = JOptionPane.showInputDialog(null, "Ingrese el usuario:");
            userValidator.validUsername(username);

            // Solicitar la contraseña
            String password = JOptionPane.showInputDialog(null, "Ingrese la contraseña:");
            userValidator.validPassword(password);

            // Crear el objeto UserDto
            UserDto userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setPassword(password);

            // Intentar iniciar sesión
            this.service.login(userDto);

            // Verificar el rol del usuario
            if (roles.get(userDto.getRol()) == null) {
                throw new Exception("Rol inválido");
            }

            // Iniciar la sesión según el rol
            roles.get(userDto.getRol()).session();

        } catch (Exception e) {
            // Mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
            throw new Exception(e.getMessage());
        }
    }

}


