package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.MeasurementService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;


    @GetMapping
    public List<Measurement> getAllMeasurement(){
       return measurementService.getAllMeasurements();
    }

    @GetMapping(value = "/{id}")
    public List<Measurement> getMeasurementsById(@PathVariable Integer id){
        Measurement measurementByIdService = measurementService.getMeasurementByIdService(id);
        return Collections.singletonList(measurementByIdService);
    }

    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement){
        return measurementService.addMeasurementService(measurement);
    }
    @DeleteMapping(value = "/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        return measurementService.deleteMeasurementByIdService(id);
    }
    @PutMapping(value = "/{id}")
    public Result editMeasurement(@PathVariable Integer id,@RequestBody Measurement measurement){
      return measurementService.editMeasurement(id,measurement);
    }

}

