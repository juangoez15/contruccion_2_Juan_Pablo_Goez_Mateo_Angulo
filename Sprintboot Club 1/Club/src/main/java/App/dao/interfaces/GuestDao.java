/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package App.dao.interfaces;

import App.dto.GuestDto;
import App.dto.PersonDto;

/**
 *
 * @author ASUS
 */
public interface GuestDao {

    public boolean existsByPersonId(PersonDto personId)throws Exception;

    public void createGuest(GuestDto guestDto) throws Exception;

    public void createPerson(GuestDto guestDto)throws Exception;

    public GuestDto findById(long guestId);
    
}
