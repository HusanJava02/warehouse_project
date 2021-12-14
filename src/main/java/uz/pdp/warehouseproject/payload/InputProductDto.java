package uz.pdp.warehouseproject.payload;

import lombok.Data;
import uz.pdp.warehouseproject.entity.Input;
import uz.pdp.warehouseproject.entity.Product;

import javax.persistence.*;
import java.util.Date;

@Data
public class InputProductDto {
    private Integer id;
    private Integer productId;
    private Double amount;
    private Double price;
    private Long expiredDate;
    private Integer inputId;
}
