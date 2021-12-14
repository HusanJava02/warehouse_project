package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.MeasurementRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement){
        if (measurementRepository.existsByName(measurement.getName())) {
            new Result(false,"the name of Measurement already exists");
        }
        measurementRepository.save(measurement);
        return new Result(true,"saved successfully");
    }


    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementByIdService(Integer id) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        if (measurementOptional.isPresent()){
           return measurementOptional.get();
        }
        return null;
    }

    public Result deleteMeasurementByIdService(Integer id) {
        if (measurementRepository.existsById(id)){
            measurementRepository.deleteById(id);
            return new Result(true,"succesfully deleted");
        }else return new Result(false,"Measurement not found");
    }

    public Result editMeasurement(Integer id, Measurement measurement) {
        Measurement measurementEditing = new Measurement();
        measurement.setActive(measurement.getActive());
        measurement.setName(measurement.getName());
        measurementRepository.save(measurementEditing);
        return new Result(true,"Measurement id edited");
    }
}
