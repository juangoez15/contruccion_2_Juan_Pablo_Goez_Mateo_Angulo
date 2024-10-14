package App.controller.validator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class UserValidator extends CommonsValidator{
    public void validUsername(String username) throws Exception {
        super.isValidString("el nombre de usuario ", username);
    }

    public void validPassword(String password) throws Exception {
        super.isValidString("la contraseña de usuario ", password);
    }

    public void validRole(String role) throws Exception {
        super.isValidString("el rol de usuario ", role);
    }
}
