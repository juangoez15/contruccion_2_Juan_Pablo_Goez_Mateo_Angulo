package App.Helper;

import App.dto.GuestDto;
import App.dto.PartnerDto;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.model.Guest;
import App.model.Partner;
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

    public static PartnerDto parse(Partner partner) {
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setId(partner.getId());
        partnerDto.setAmaunt(partner.getAmaunt());
        partnerDto.setType(partner.isType());
        partnerDto.setCreation_date(partner.getCreation_date());
        partnerDto.setUserId(parse(partner.getUserId()));
        return partnerDto;
    }

    public static Partner parse(PartnerDto partnerDto) {
        Partner partner = new Partner();
        partner.setId(partnerDto.getId());
        partner.setAmaunt(partnerDto.getAmaunt());
        partner.setType(partnerDto.isType());
        partner.setCreation_date(partnerDto.getCreation_date());
        partner.setUserId(parse(partnerDto.getUserId()));
        return partner;
    }

    public static GuestDto parse(Guest guest) {
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        guestDto.setPartnerId(guestDto.getPartnerId());
        return guestDto;
    }

    public static Guest parse(GuestDto guestDto) {
        Guest guest = new Guest();
        guest.setId(guestDto.getId());
        guest.setUSerId(guestDto.getUserId());
        return guest;
    }



    public static User getUserId(long aLong) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
    

}
