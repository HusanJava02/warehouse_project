package uz.pdp.warehouseproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uz.pdp.warehouseproject.templates.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category extends AbsEntity {
    @ManyToOne
    private Category parentCategory;

    public Category(Integer id, String name, Boolean active, Category parentCategory) {
        super(id, name, active);
        this.parentCategory = parentCategory;
    }
}


