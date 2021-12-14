package uz.pdp.warehouseproject.payload;

import lombok.Data;
import uz.pdp.warehouseproject.entity.Warehouse;

import javax.persistence.*;
import java.util.List;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private Boolean active;
    private List<Integer> warehouseId;
}
