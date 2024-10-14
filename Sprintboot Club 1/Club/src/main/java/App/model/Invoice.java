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
    private long id;
    @Column(name = "CREATEDATE")
    private Date creacionDate;
    @JoinColumn(name = "PARTNERID")
    @OneToOne
    private Partner partnerId;
    @JoinColumn(name = "USERID")
    @OneToOne
    private User userId;
    @Column(name = "TOTALAMOUNT")
    private double totalAmount;
    @Column(name = "STATUS")
    private boolean status;

}
