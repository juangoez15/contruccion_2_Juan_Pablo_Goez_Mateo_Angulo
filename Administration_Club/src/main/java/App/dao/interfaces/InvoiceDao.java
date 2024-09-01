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
public interface InvoiceDao {
     public boolean existsByPersonId(PersonDto personDto) throws Exception;

    public void createPartner(GuestDto guestDto) throws Exception;
    
    
}
