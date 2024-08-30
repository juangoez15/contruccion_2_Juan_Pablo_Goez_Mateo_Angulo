package App.dto;


public class PersonDto {
    private long Id;
    private long Identification_Card;
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

    public long getIdentification_Card() {
        return Identification_Card;
    }

    public void setIdentification_Card(long Identification_Card) {
        this.Identification_Card = Identification_Card;
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
