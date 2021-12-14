package uz.pdp.warehouseproject.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Supplier;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.payload.SupplierDto;
import uz.pdp.warehouseproject.service.SupplierService;

import java.util.List;

@RequestMapping(value = "/supplier")
@RestController
public class SupplierController {
    SupplierService supplierService;

    @GetMapping
    public List<Supplier> getSupplier(){
        return supplierService.getAllSupplierService();
    }

    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @PutMapping(value = "/{id}")
    public Result editSupplier(@PathVariable Integer id,@RequestBody Supplier supplier){
        return supplierService.editSupplier(id, supplier);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteSupplier(@PathVariable Integer id){
        return supplierService.deleteSupplier(id);
    }

}
