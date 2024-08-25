package App.controller.validator;


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
