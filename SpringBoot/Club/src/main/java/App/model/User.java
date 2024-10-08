
package App.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="User")

public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private long Id;
    @Column(name="USERNAME")
    private String username;
    @Column(name="PASSWORD")
    private String Password;
    @Column(name="ROLE")
    private String Rol;
    @JoinColumn(name="PERSONID")
    @OneToOne
    private Person personId;
    

    
}
