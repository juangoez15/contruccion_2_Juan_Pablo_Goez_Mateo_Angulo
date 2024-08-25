package App;

import App.config.MYSQLConnection;
import App.controller.ControllerInterface;
import App.controller.validator.LoginController;



public class AdministrationClub {

    public static void main(String[] args) throws Exception {
		ControllerInterface controller = new LoginController();
		try {
			controller.session();
			MYSQLConnection.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
