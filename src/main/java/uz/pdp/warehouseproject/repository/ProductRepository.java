package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
