package App.service;


public interface PartnerService {

    public  void changeSubscription(long id, String newtype);

    public  String getInvoices(long id);

    public void addFunds(int i, double amount);
    
}
