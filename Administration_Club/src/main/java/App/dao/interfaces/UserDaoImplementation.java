package App.dao.interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import App.config.MYSQLConnection;
import App.dao.interfaces.UserDao;
import App.dto.UserDto;
import App.helpers.Helper;
import App.model.Person;
import App.model.User;
import Helper.Helper;

public class UserDaoImplementation implements UserDao {

    @Override
    public UserDto findByUserName(UserDto userDto) throws Exception {
        String query = "SELECT ID,USERNAME,PASSWORD,ROLE,PERSONID FROM USER WHERE USERNAME = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userDto.getUsername());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            User user = new User();
            user.setId(resulSet.getLong("ID"));
            user.setUsername(resulSet.getString("USERNAME"));
            user.setPassword(resulSet.getString("PASSWORD"));
            user.setRol(resulSet.getString("ROLE"));
            Person person = new Person();
            person.setIdentification_Card(resulSet.getLong("PERSONID"));
            user.setPersonId(person);
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(user);
        }
        resulSet.close();
        preparedStatement.close();
        return null;

    }

    @Override
    public boolean existsByUserName(UserDto userDto) throws Exception {
        String query = "SELECT 1 FROM USER WHERE USERNAME = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userDto.getUsername());
        ResultSet resulSet = preparedStatement.executeQuery();
        boolean exists = resulSet.next();
        resulSet.close();
        preparedStatement.close();
        return exists;
    }

    @Override
    public void createUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        String query = "INSERT INTO USER(USERNAME,PASSWORD,PERSONID,ROLE) VALUES (?,?,?,?) ";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setLong(3, user.getPersonId().getId());
        preparedStatement.setString(4, user.getRol());
        preparedStatement.execute();
        preparedStatement.close();
    }
}
