/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.dao.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import app.config.MYSQLConnection;
import App.dao.interfaces.PersonDao;
import App.dto.PersonDto;
import App.model.Person;
import Helper.Helper;

public class PersonDaoImplementation implements PersonDao {

    @Override
    public boolean existsByDocument(PersonDto personDto) throws Exception {
        String query = "SELECT 1 FROM PERSON WHERE DOCUMENT = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, personDto.getIdentification_Card());
        ResultSet resulSet = preparedStatement.executeQuery();
        boolean exists = resulSet.next();
        resulSet.close();
        preparedStatement.close();
        return exists;
    }

    @Override
    public void createPerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        String query = "INSERT INTO PERSON(NAME,DOCUMENT,AGE) VALUES (?,?,?) ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, person.getName());
        preparedStatement.setLong(2, person.getIdentification_Card());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deletePerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        String query = "DELETE FROM PERSON WHERE DOCUMENT = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, person.getIdentification_Card());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public PersonDto findByDocument(PersonDto personDto) throws Exception {
        String query = "SELECT ID,NAME,DOCUMENT,AGE FROM PERSON WHERE DOCUMENT = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, personDto.getIdentification_Card());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Person person = new Person();
            person.setId(resulSet.getLong("ID"));
            person.setName(resulSet.getString("NAME"));
            person.setIdentification_Card(resulSet.getLong("DOCUMENT"));
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(person);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

}
