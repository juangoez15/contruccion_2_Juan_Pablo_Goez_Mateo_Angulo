/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.service.interfaces;

import App.dto.GuestDto;

/**
 *
 * @author Matt
 */
public interface GuestService {

    public GuestDto findById(long guestId);

    public boolean convertToPartner(long guestId);
    
}
