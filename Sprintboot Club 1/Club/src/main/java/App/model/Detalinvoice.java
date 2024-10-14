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
@Table(name = "invoice")

public class Detalinvoice {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @JoinColumn(name = "INVOICEID")
    @OneToOne
    private Invoice invoiceId;
    @Column(name = "ITEM")
    private int item;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "AMOUNT")
    private double amount;
    
}

