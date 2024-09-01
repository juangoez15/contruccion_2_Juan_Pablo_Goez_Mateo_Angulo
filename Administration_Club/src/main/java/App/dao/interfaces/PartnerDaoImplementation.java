package App.dao.interfaces;

import App.config.MYSQLConnection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import App.dto.PartnerDto;

import App.dto.GuestDto;

import App.model.Partner;

import App.Helper.Helper;

import App.dto.PersonDto;

import java.sql.Date;

public class PartnerDaoImplementation implements PartnerDao {

    public PartnerDto findByPersonId(PartnerDto partnerDto) throws Exception {
        String query = "SELECT ID, AMOUNT, TYPE, CREATIONDATE, USERID FROM PARTNER WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getUserId().getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Partner partner = new Partner();
            partner.setId(resultSet.getLong("ID"));
            partner.setUserId(Helper.getUserId(resultSet.getLong("USERID")));
            partner.setAmaunt(resultSet.getDouble("AMOUNT"));
            partner.setType(resultSet.getBoolean("TYPE"));
            partner.setCreation_date(resultSet.getDate("CREATIONDATE"));

            resultSet.close();
            preparedStatement.close();
            return Helper.parse(partner);
        }
        resultSet.close();
        preparedStatement.close();
        return null;

    }

    public boolean existsById(PartnerDto partnerDto) throws Exception {
        String query = "SELECT 1 FROM PARTNER WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean exists = resultSet.next();
        resultSet.close();
        preparedStatement.close();
        return exists;

    }

    @Override

    public void createPartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto);
        String query = "INSERT INTO PARTNER( USERID, AMOUNT, TYPE, CREATIONDATE) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partner.getUserId().getId());
        preparedStatement.setDouble(2, partner.getAmaunt());
        preparedStatement.setBoolean(3, partner.isType());
        preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
        preparedStatement.execute();
        preparedStatement.close();

    }

    public void deletePartner(PartnerDto partnerDto) throws Exception {

        String query = "DELETE FROM PARTNER WHERE USERID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);

        preparedStatement.setLong(1, partnerDto.getUserId().getId());

        preparedStatement.execute();

        preparedStatement.close();

    }

    // Método para activar un invitado
    public void activateGuest(long guestId) throws Exception {

        String query = "UPDATE GUEST SET ACTIVE = ? WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);

        preparedStatement.setBoolean(1, true);

        preparedStatement.setLong(2, guestId);

        preparedStatement.executeUpdate();

        preparedStatement.close();

    }

    // Método para desactivar un invitado
    public void deactivateGuest(long guestId) throws Exception {

        String query = "UPDATE GUEST SET ACTIVE = ? WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);

        preparedStatement.setBoolean(1, false);

        preparedStatement.setLong(2, guestId);

        preparedStatement.executeUpdate();

        preparedStatement.close();

    }

    // Método para incrementar fondos del socio
    public void addFunds(long partnerId, double amount) throws Exception {

        String query = "UPDATE PARTNER SET AMOUNT = AMOUNT + ? WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);

        preparedStatement.setDouble(1, amount);

        preparedStatement.setLong(2, partnerId);

        preparedStatement.executeUpdate();

        preparedStatement.close();

    }

    // Método para registrar un consumo
    public void makeConsumption(long partnerId, double amount) throws Exception {

        String query = "UPDATE PARTNER SET AMOUNT = AMOUNT - ? WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);

        preparedStatement.setDouble(1, amount);

        preparedStatement.setLong(2, partnerId);

        preparedStatement.executeUpdate();

        preparedStatement.close();

    }

    // Método para solicitar la baja del socio
    public void requestDropout(long partnerId) throws Exception {

        String query = "UPDATE PARTNER SET STATUS = ? WHERE ID = ?";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);

        preparedStatement.setString(1, "PENDING_DROPOUT");

        preparedStatement.setLong(2, partnerId);

        preparedStatement.executeUpdate();

        preparedStatement.close();

    }

    @Override
    public boolean existsByPersonId(PersonDto personDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existsById(long partnerId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PartnerDto findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
