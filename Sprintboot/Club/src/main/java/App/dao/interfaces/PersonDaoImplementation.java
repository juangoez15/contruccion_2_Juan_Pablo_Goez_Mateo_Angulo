package App.dao.interfaces;

import App.config.MYSQLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import App.dao.interfaces.PersonDao;
import App.dto.PersonDto;
import App.model.Person;
import App.Helper.Helper;
import App.dao.interfaces.repository.PersonRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service

public class PersonDaoImplementation implements PersonDao {

    @Autowired

    PersonRepository personRepository;

    @Override
    public boolean existsByDocument(PersonDto personDto) throws Exception {

        return personRepository.existsByDocument(personDto.getDocument());
    }

    @Override
    public void createPerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        personRepository.save(person);
        personDto.setId(person.getId());
    }

    @Override
    public void deletePerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        personRepository.delete(person);
    }

    @Override
    public PersonDto findByDocument(PersonDto personDto) throws Exception {
        Person person = personRepository.findByDocument(personDto.getDocument());
        return Helper.parse(person);
    }

}
