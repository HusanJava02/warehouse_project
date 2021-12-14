package uz.pdp.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Timestamp date;

    @OneToOne(optional = false)
    private Warehouse warehouse;

    @ManyToOne
    private Supplier supplier;

    @OneToOne
    private Currency currency;

    @Column(nullable = false)
    private Long factureNumber;

    @Column(unique = true,nullable = false)
    private String code;



}
