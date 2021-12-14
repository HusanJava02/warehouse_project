package uz.pdp.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.warehouseproject.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    public boolean existsByName(String name);
}
