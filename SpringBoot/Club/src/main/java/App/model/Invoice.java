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
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "invoice")

public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long Id;
    @Column(name = "CREATEDATE")
    private Date CreacionDate;
    @JoinColumn(name = "PARTNERID")
    @OneToOne
    private Partner PartnerId;
    @JoinColumn(name = "USERID")
    @OneToOne
    private User UserId;
    @Column(name = "TOTALAMOUNT")
    private double TotalAmount;
    @Column(name = "STATUS")
    private boolean Status;

}
