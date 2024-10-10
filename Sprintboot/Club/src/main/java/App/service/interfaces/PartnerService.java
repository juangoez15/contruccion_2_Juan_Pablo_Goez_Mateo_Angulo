/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package App.service.interfaces;

/**
 *
 * @author ASUS
 */
public interface PartnerService {

    public void changeSubscription(long id, String newType);

    public String getInvoices(long id);
    
}
