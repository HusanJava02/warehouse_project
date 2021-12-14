package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

}
