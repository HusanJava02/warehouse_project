package uz.pdp.warehouseproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.entity.Supplier;
import uz.pdp.warehouseproject.entity.Warehouse;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputDto {
    private Long inputDate;
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private Long factureNumber;
    private String code;

}
