package App.dto;

public class DetalinvoiceDto {
    private long Id;
    private InvoiceDto Invoiceid;
    private int Item;
    private String Description;
    private double Amount;
    
    public DetalinvoiceDto(){
        
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public InvoiceDto getInvoiceid() {
        return Invoiceid;
    }

    public void setInvoiceid(InvoiceDto Invoiceid) {
        this.Invoiceid = Invoiceid;
    }

    public int getItem() {
        return Item;
    }

    public void setItem(int Item) {
        this.Item = Item;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }
    
    
}
