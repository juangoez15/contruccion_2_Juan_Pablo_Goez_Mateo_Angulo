package App.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")

public class Partner {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private long Id;
    @Column(name="AMOUNT")    
    private double amaunt;
    @Column(name="TYPE")
    private boolean type;
    @Column(name="CREATEDATE")//Preguntar si esta si se puede así 
    private Date creationdate;
    @JoinColumn(name="USERID")
    @OneToOne
    private User UserId;
    
}
