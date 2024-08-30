package App.dao.interfaces;

import App.config.MYSQLConnection;
import App.dto.GuestDto;
import App.model.Guest;
import App.Helper.Helper;
import App.dto.PersonDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GuestDaoImplementation implements GuestDao {

    // Método para encontrar un invitado por ID
    public GuestDto findById(GuestDto guestDto) throws Exception {
        String query = "SELECT ID, NAME, PARTNERID, ACTIVE FROM GUEST WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, guestDto.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Guest guest = new Guest();
            guest.setId(resultSet.getLong("ID"));
            guest.setName(resultSet.getString("NAME"));
            guest.setPartnerId(Helper.getPartnerId(resultSet.getLong("PARTNERID")));

            resultSet.close();
            preparedStatement.close();

            return Helper.parse(guest);
        }

        resultSet.close();
        preparedStatement.close();

        return null;
    }

    public boolean existsById(GuestDto guestDto) throws Exception {
        String query = "SELECT 1 FROM GUEST WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, guestDto.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean exists = resultSet.next();

        resultSet.close();
        preparedStatement.close();

        return exists;
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        Guest guest = Helper.parse(guestDto);
        String query = "INSERT INTO GUEST(NAME, PARTNERID, STATUS) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, guestDto.getName());
        preparedStatement.setLong(2, guestDto.getId());
        preparedStatement.setBoolean(3, true); // Los invitados son creados como activos por defecto

        preparedStatement.execute();
        preparedStatement.close();
    }

    public void deleteGuest(GuestDto guestDto) throws Exception {
        String query = "DELETE FROM GUEST WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, guestDto.getId());

        preparedStatement.execute();
        preparedStatement.close();
    }

    // Método para activar un invitado
    public void activateGuest(GuestDto guestDto) throws Exception {
        String query = "UPDATE GUEST SET ACTIVE = ? WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setBoolean(1, true);
        preparedStatement.setLong(2, guestDto.getId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    // Método para desactivar un invitado
    public void deactivateGuest(GuestDto guestDto) throws Exception {
        String query = "UPDATE GUEST SET ACTIVE = ? WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setBoolean(1, false);
        preparedStatement.setLong(2, guestDto.getId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    // Método para actualizar el nombre del invitado
    public void updateGuestName(GuestDto guestDto, String newName) throws Exception {
        String query = "UPDATE GUEST SET NAME = ? WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, newName);
        preparedStatement.setLong(2, guestDto.getId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public boolean existsByPersonId(PersonDto personId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
