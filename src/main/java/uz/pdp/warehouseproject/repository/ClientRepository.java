package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Category;
import uz.pdp.warehouseproject.entity.Client;

public interface ClientRepository extends JpaRepository<Client,Integer> {

}
