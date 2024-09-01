/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package App.dao.interfaces;

import App.dto.PartnerDto;
import App.dto.PersonDto;

public interface PartnerDao {

    public boolean existsByPersonId(PersonDto personDto) throws Exception;

    public void createPartner(PartnerDto partnerDto) throws Exception;

    public boolean existsById(long partnerId);

    public PartnerDto findById(long id);

}
