package uz.pdp.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.warehouseproject.templates.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Data
public class Product extends AbsEntity {
    public Product(Integer id, String name, Boolean active, Category category, Attachment attachment, String code, Measurement measurement) {
        super(id, name, active);
        this.category = category;
        this.attachment = attachment;
        this.code = code;
        this.measurement = measurement;
    }

    @OneToOne
    private Category category;

    @OneToOne
    private Attachment attachment;

    @Column(nullable = false)
    private String code;

    @OneToOne
    private Measurement measurement;



}
