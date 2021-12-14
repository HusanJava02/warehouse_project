package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Warehouse;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.WarehouseService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@RequestMapping(value = "/warehouse")
@RestController
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }

    @GetMapping(value = "/{id}")
    public Warehouse getWarehouseById(@PathVariable Integer id) {

        return warehouseService.getWarehousesById(id);
    }
    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteWarehouse(@PathVariable Integer id){
        return warehouseService.deleteWarehouseById(id);
    }
    @PutMapping(value = "/{id}")
    public Result editWarehouse(@PathVariable Warehouse warehouse,@PathVariable Integer id){
        return warehouseService.editWarehouse(id,warehouse);
    }




}

