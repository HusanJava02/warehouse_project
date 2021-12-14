package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.User;
import uz.pdp.warehouseproject.entity.Warehouse;


public interface UserRepository extends JpaRepository<User,Integer> {

}
