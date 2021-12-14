package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.entity.Warehouse;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(Warehouse warehouse) {
        if (warehouseRepository.existsByName(warehouse.getName())) {
            new Result(false, "the name of Warehouse already exists");
        }
        warehouseRepository.save(warehouse);
        return new Result(true, "saved successfully");
    }


    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehousesById(Integer id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        if (warehouse.isPresent()) {
            return warehouse.get();
        }
        return null;
    }

    public Result deleteWarehouseById(Integer id) {
        if (warehouseRepository.existsById(id)) {
            warehouseRepository.deleteById(id);
            return new Result(true, "succesfully deleted");
        } else return new Result(false, "Measurement not found");
    }

    public Result editWarehouse(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (!optionalWarehouse.isPresent())
            return new Result(false, "warehouse not found");
        Warehouse editingWarehouse = optionalWarehouse.get();
        editingWarehouse.setActive(warehouse.getActive());
        editingWarehouse.setName(warehouse.getName());
        warehouseRepository.save(warehouse);
        return new Result(true, "Warehouse edited");
    }
}
