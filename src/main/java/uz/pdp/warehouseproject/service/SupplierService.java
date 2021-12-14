package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.warehouseproject.entity.Supplier;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.payload.SupplierDto;
import uz.pdp.warehouseproject.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public List<Supplier> getAllSupplierService() {
        return supplierRepository.findAll();
    }

    public Result addSupplier(Supplier supplier) {
        Supplier savingSupplier = new Supplier(null, supplier.getName(), supplier.getActive(), supplier.getPhoneNumber());
        supplierRepository.save(savingSupplier);
        return new Result(true, "supplier saved");
    }

    public Result editSupplier(Integer id, Supplier supplier) {
        Optional<Supplier> optional = supplierRepository.findById(id);
        if (optional.isPresent()) {
            Supplier editingSupplier = optional.get();
            editingSupplier.setName(editingSupplier.getName());
            editingSupplier.setPhoneNumber(supplier.getPhoneNumber());
            editingSupplier.setActive(supplier.getActive());
            supplierRepository.save(editingSupplier);
            return new Result(true,"succesfully edited");
        }else return new Result(false,"supplier not found");
    }

    public Result deleteSupplier(Integer id){
        if (supplierRepository.existsById(id)){
            supplierRepository.deleteById(id);
            return new Result(true,"succesfully deleted");
        }
        return new Result(false,"not found supplier");
    }

}
