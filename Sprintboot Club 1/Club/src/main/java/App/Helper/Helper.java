package App.Helper;

import App.dto.GuestDto;
import App.dto.PartnerDto;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.model.Guest;
import App.model.Partner;
import App.model.Person;
import App.model.User;
import java.sql.Timestamp;

public abstract class Helper {

    public static PersonDto parse(Person person) {
        if (person == null) {
            return null;
        }
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setDocument(person.getDocument());
        personDto.setName(person.getName());
        return personDto;
    }

    public static Person parse(PersonDto personDto) {
        if (personDto == null) {
            return null;
        }
        Person person = new Person();
        person.setId(personDto.getId());
        person.setDocument(personDto.getDocument());
        person.setName(personDto.getName());
        return person;
    }

    public static UserDto parse(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setPersonId(parse(user.getPersonId()));
        userDto.setRol(user.getRol());
        userDto.setUserName(user.getUserName());
        return userDto;
    }

    public static User parse(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setPersonId(parse(userDto.getPersonId()));
        user.setRol(userDto.getRol());
        user.setUserName(userDto.getUserName());
        return user;
    }

    public static PartnerDto parse(Partner partner) {
        if (partner == null) {
            return null;
        }
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setId(partner.getId());
        partnerDto.setAmaunt(partner.getAmaunt());
        partnerDto.setType(partner.isType());
        partnerDto.setCreationDate((Timestamp) partner.getCreationDate());
        partnerDto.setUserId(parse(partner.getUserId()));
        return partnerDto;
    }

    public static Partner parse(PartnerDto partnerDto) {
        if (partnerDto == null) {
            return null;
        }
        Partner partner = new Partner();
        partner.setId(partnerDto.getId());
        partner.setAmaunt(partnerDto.getAmaunt());
        partner.setType(partnerDto.isType());
        partner.setCreationDate(partnerDto.getCreationDate());
        partner.setUserId(parse(partnerDto.getUserId()));
        return partner;
    }

    public static GuestDto parse(Guest guest) {
        if (guest == null) {
            return null;
        }
        GuestDto guestDto = new GuestDto();
        guestDto.setId(guest.getId());
        guestDto.setStatus(guest.isStatus());
        guestDto.setUserId(parse(guest.getUserId()));
        guestDto.setPartnerId(parse(guest.getPartnerId()));

        return guestDto;
    }

    public static Guest parse(GuestDto guestDto) {
        if (guestDto == null) {
            return null;
        }
        Guest guest = new Guest();
        guest.setId(guestDto.getId());
        guest.setStatus(guestDto.getStatus());
        guest.setUserId(parse(guestDto.getUserId()));
        guest.setPartnerId(parse(guestDto.getPartnerId()));

        return guest;
    }


}
