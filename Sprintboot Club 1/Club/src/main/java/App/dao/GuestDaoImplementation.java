/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.dao;

import App.Helper.Helper;
import App.dao.interfaces.GuestDao;
import App.dao.interfaces.repository.GuestRepository;
import App.dao.interfaces.repository.PartnerRepository;
import App.dao.interfaces.repository.PersonRepository;
import App.dto.GuestDto;
import App.dto.PersonDto;
import App.dto.UserDto;
import App.model.Guest;
import App.model.Partner;
import App.model.Person;
import App.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@Service
public class GuestDaoImplementation implements GuestDao {

    @Autowired

    private PersonRepository personRepository;
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    PartnerRepository partnerRepository;

    public PersonDto findByUserId(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        return Helper.parse(guestRepository.findByUserId(user));
    }

    @Override
    public boolean existsByPersonId(PersonDto personDto) throws Exception {
        return partnerRepository.existsById(personDto.getId());
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

    public void createPerson(GuestDto guestDto) throws Exception {
        Guest person = Helper.parse(guestDto);
        personRepository.save(person);
        guestDto.setId(person.getId());
    }

    @Override
    public GuestDto findById(long guestId) {
        Guest guest = guestRepository.findById(guestId).orElse(null);

        if (guest == null) {
            System.out.println("El invitado con ID " + guestId + " no existe.");
            return null; 
        }

        GuestDto guestDto = Helper.parse(guest);

        return guestDto;
    }
    
    

}
