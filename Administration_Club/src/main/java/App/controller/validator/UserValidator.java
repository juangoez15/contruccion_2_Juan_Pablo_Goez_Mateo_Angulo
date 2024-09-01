package App.controller.validator;

public class UserValidator extends CommonsValidator {

    public void validUsername(String username) throws Exception {
        super.isValidString("username", username);
    }

    public void validPassword(String password) throws Exception {
        super.isValidString("user password", password);
    }

    public void validRole(String role) throws Exception {
        super.isValidString("user role", role);
    }
}
