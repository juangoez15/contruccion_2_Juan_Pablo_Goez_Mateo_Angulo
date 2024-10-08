package App.dao.interfaces;
 
import App.config.MYSQLConnection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import App.dto.PartnerDto;

import App.dto.GuestDto;

import App.model.Partner;

import App.Helper.Helper;

import App.dto.PersonDto;
import App.dao.interfaces.repository.PartnerRepository;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
 
public class PartnerDaoImplementation implements PartnerDao {
    
    @Autowired
    PartnerRepository partnerRepository;
 
    public PartnerDto findByPartnerId(PartnerDto partnerDto) throws Exception {

       Partner partner = partnerRepository.findById(partnerDto.getId());
       return Helper.parse(partner); 

    }
 
    @Override

    public boolean existsByPersonId(PersonDto personDto) throws Exception {

        return partnerRepository.existsById(personDto.getId());
    }
 
    @Override

    public void createPartner(PartnerDto partnerDto) throws Exception { // este metodo de crear partner me pide crear un meto de createPartner en la interface PartnerRepository

        Partner partner = Helper.parse(partnerDto);
        partnerRepository.save(partner);

    }
 
    public void deletePartner(PartnerDto partnerDto) throws Exception { // este metodo de eliminar partner me pide crear un meto de deletePartner en la interface PartnerRepository

        Partner partner = Helper.parse(partnerDto);
        partnerRepository.delete(partner);

    }
 
    // Método para crear un invitado

    public void createGuest(GuestDto guestDto) throws Exception {

        String query = "INSERT INTO GUEST(NAME, PARTNER_ID, ACTIVE) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);

        preparedStatement.setString(1, guestDto.getName());

        preparedStatement.setLong(2, guestDto.getPartnerId());

        preparedStatement.setBoolean(3, true); // Los invitados son creados como activos por defecto

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

}

 