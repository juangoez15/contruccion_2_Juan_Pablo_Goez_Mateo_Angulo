package App.dao.interfaces;

import App.config.MYSQLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import App.dto.PartnerDto;
import App.model.Partner;
import App.Helper.Helper;
import App.dto.PersonDto;
import java.sql.Date;

public class PartnerDaoImplementation implements PartnerDao {

    public PartnerDto findByPersonId(PartnerDto partnerDto) throws Exception {
        String query = "SELECT ID, AMOUNT, TYPE, CREATION_DATE, USER_ID FROM PARTNER WHERE USER_ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getUserId().getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Partner partner = new Partner();
            partner.setId(resultSet.getLong("ID"));
            partner.setAmaunt(resultSet.getDouble("AMOUNT"));
            partner.setType(resultSet.getBoolean("TYPE"));
            partner.setCreation_date(resultSet.getDate("CREATION_DATE"));
            // Asumiendo que hay un método en Helper para convertir UserId a un objeto User
            partner.setUserId(Helper.getUserId(resultSet.getLong("USER_ID")));
            resultSet.close();
            preparedStatement.close();
            return Helper.parse(partner);
        }
        resultSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public boolean existsByPersonId(PersonDto personId) throws Exception {
        String query = "SELECT 1 FROM PARTNER WHERE USER_ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, personId.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean exists = resultSet.next();
        resultSet.close();
        preparedStatement.close();
        return exists;
    }

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto);
        String query = "INSERT INTO PARTNER(AMOUNT, TYPE, CREATION_DATE, USER_ID) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setDouble(1, partner.getAmaunt());
        preparedStatement.setBoolean(2, partner.isType());
        preparedStatement.setDate(3, (Date) partner.getCreation_date());
        preparedStatement.setLong(4, partner.getUserId().getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void deletePartner(PartnerDto partnerDto) throws Exception {
        String query = "DELETE FROM PARTNER WHERE USER_ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getUserId().getId());
        preparedStatement.execute();
        preparedStatement.close();
    }

}
