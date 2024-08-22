
package App.Helper;

import App.dto.PersonDto;
import App.dto.UserDto;
import App.model.Person;
import App.model.User;

public abstract class Helper {
 
	public static PersonDto parse(Person person) {
		PersonDto personDto = new PersonDto();
		personDto.setId(person.getId());
		personDto.setIdentification_Card(person.getIdentification_Card());
		personDto.setName(person.getName());
		return personDto;
	}
	public static Person parse(PersonDto personDto) {
		Person person = new Person();
		person.setId(personDto.getId());
		person.setIdentification_Card(personDto.getIdentification_Card());
		person.setName(personDto.getName());
		return person;
	}
	public static UserDto parse(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setPassword(user.getPassword());
		userDto.setPersonId(parse(user.getPersonId()));
		userDto.setRol(user.getRol());
		userDto.setUsername(user.getUsername());
		return userDto;
	}
	public static User parse(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setPassword(userDto.getPassword());
		user.setPersonId(parse(userDto.getPersonId()));
		user.setRol(userDto.getRol());
		user.setUsername(userDto.getUsername());
		return user;
	}
}
