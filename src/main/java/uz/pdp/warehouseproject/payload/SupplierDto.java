package uz.pdp.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.warehouseproject.templates.AbsEntity;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto extends AbsEntity {
    private String phoneNumber;

    public SupplierDto(Integer id, String name, Boolean active, String phoneNumber) {
        super(id, name, active);
        this.phoneNumber = phoneNumber;
    }
}
