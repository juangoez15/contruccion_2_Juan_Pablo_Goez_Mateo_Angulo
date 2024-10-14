package App.dao;

import App.config.MYSQLConnection;

import java.sql.PreparedStatement;
import App.dto.PartnerDto;
import App.dto.GuestDto;
import App.model.Partner;
import App.Helper.Helper;
import App.dao.interfaces.PartnerDao;
import App.dao.interfaces.repository.GuestRepository;

import App.dto.PersonDto;
import App.dao.interfaces.repository.PartnerRepository;
import App.dto.UserDto;
import App.model.Guest;
import App.model.User;

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
    private GuestRepository guestRepository;

    @Autowired
    PartnerRepository partnerRepository;

    public PartnerDto findByPartnerId(PartnerDto partnerDto) throws Exception {

        Partner partner = partnerRepository.findById(partnerDto.getId());
        if (partner == null) {
            return null;
        }
        return Helper.parse(partner);
    }

    @Override
    public boolean existsByPersonId(PersonDto personDto) throws Exception {

        return partnerRepository.existsById(personDto.getId());
    }

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto); // Conversión DTO a entidad
        partnerRepository.save(partner); // No necesitas implementar nada más, JPA maneja el `save`
    }

    public void deletePartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto); // Conversión DTO a entidad
        partnerRepository.delete(partner); // Uso de `delete`
    }

    public void createGuest(GuestDto guestDto) throws Exception {
        Guest guest = Helper.parse(guestDto);
        Partner partner = partnerRepository.findById(guestDto.getPartnerId().getId());
        if (partner == null) {
            throw new Exception("The associated partner does not exist.");
        }
        guest.setPartnerId(partner);
        guest.setStatus(false);
        guestRepository.save(guest);
    }

    // Método para activar un invitado
    public void setGuestStatus(long guestId, boolean isActive) throws Exception {
        String query = "UPDATE GUEST SET ACTIVE = ? WHERE ID = ?";
        try (PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query)) {
            preparedStatement.setBoolean(1, isActive);
            preparedStatement.setLong(2, guestId);
            preparedStatement.executeUpdate();
        }
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

    
    public PartnerDto findByUserId(UserDto userDto) throws Exception {
         User user = Helper.parse(userDto);
         return Helper.parse(partnerRepository.findByUserId(user));
    }

}
