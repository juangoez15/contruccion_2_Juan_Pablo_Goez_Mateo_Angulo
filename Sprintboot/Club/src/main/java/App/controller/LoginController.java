package App.controller;

import java.util.HashMap;
import java.util.Map;
import App.controller.validator.UserValidator;
import App.dto.UserDto;
import App.service.interfaces.LoginService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Setter
@Getter
public class LoginController implements ControllerInterface {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private LoginService service;
    private static final String MENU = "Ingrese la opcion que desea: \n 1. para iniciar sesion. \n 2. para detener la ejecucion.";
    private Map<String, ControllerInterface> roles;

    public LoginController(AdminController adminController, PartnerController partnerController) {
        this.roles = new HashMap<String, ControllerInterface>();
        roles.put("admin", adminController);
        roles.put("Socio", partnerController);
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
            System.out.println("ingrese el  nombre de usuario");
            String username = Utils.getReader().nextLine(); // "Ingrese el usuario:");
            userValidator.validUsername(username);

            // Solicitar la contraseña
            System.out.println("Ingrese la contraseña:");
            String password = Utils.getReader().nextLine();
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
            System.out.println("Error de Inicio de Sesión: " + e.getMessage());
            for (StackTraceElement el : e.getStackTrace()) {

                System.out.println(el.toString());
            }

            throw new Exception(e.getMessage());

        }
    }

}
