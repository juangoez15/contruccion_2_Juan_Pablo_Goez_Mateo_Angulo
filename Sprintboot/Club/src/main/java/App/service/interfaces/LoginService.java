package App.service.interfaces;

import App.dto.UserDto;

public interface LoginService {
	public void login(UserDto userDto) throws Exception;
	public void logout();
}
