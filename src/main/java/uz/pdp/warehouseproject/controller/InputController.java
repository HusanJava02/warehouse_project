package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Input;
import uz.pdp.warehouseproject.payload.InputDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.InputService;

import java.util.List;

@RestController
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }

    @GetMapping
    public List<Input> getAllInputs(){
        return inputService.getAllInputs();
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteInput(@PathVariable Integer id){
        return inputService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public Result editInputs(@PathVariable Integer id,@RequestBody InputDto inputDto){
        return inputService.editInput(inputDto);
    }
}
