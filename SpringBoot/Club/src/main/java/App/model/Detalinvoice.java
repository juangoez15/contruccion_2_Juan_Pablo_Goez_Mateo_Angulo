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
    private long Id;
    @JoinColumn(name = "INVOICEID")
    @OneToOne
    private Invoice Invoiceid;
    @Column(name = "ITEM")
    private int Item;
    @Column(name = "DESCRIPTION")
    private String Description;
    @Column(name = "AMOUNT")
    private double Amount;
    
}

