package App.dto;


public class PersonDto {
    private long Id;
    private long Document;
    private String Name;
    private long Celphone;
    
    public PersonDto(){
        
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getDocument() {
        return Document;
    }

    public void setDocument(long Document) {
        this.Document = Document;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public long getCelphone() {
        return Celphone;
    }

    public void setCelphone(long Celphone) {
        this.Celphone = Celphone;
    }
        
}
