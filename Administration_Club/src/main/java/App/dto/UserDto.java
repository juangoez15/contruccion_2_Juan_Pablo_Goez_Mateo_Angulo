package App.dto;

public class UserDto {

    private long Id;
    private String Username;
    private String Password;
    private String Rol;
    private PersonDto personId;

    public UserDto() {

    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public PersonDto getPersonId() {
        return personId;
    }

    public void setPersonId(PersonDto personId) {
        this.personId = personId;
    }

}
