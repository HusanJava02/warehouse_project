package uz.pdp.warehouseproject.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.warehouseproject.templates.AbsEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Warehouse extends AbsEntity {

}
