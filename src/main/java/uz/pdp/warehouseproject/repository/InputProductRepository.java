package uz.pdp.warehouseproject.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.InputProduct;

import java.util.List;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
    List<InputProduct> findAllByInputId(Integer input_id);
}
