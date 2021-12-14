package uz.pdp.warehouseproject.payload;

import lombok.Data;
import uz.pdp.warehouseproject.entity.Client;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.entity.Warehouse;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
public class OutputDto {
    private long date;
    private Integer warehouseId;
    private Integer currencyId;
    private Long factureNumber;
    private Long code;
    private Integer clientId;
}
