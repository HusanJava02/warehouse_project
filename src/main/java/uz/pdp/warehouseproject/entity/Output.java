package uz.pdp.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Output {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Timestamp date;

    @OneToOne
    private Warehouse warehouse;

    @OneToOne(optional = false)
    private Currency currency;

    private Long factureNumber;

    @Column(unique = true)
    private Long code;

    @ManyToOne(optional = false)
    private Client client;

}
