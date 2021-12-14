package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Output;
import uz.pdp.warehouseproject.payload.OutputDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.OutputService;

import java.util.List;

@RestController
@RequestMapping(value = "/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @GetMapping
    public List<Output> getAllOutputs(){
        return outputService.getAllOutputs();
    }

    @PostMapping
    public Result addOutputController(@RequestBody OutputDto outputDto){
        return outputService.addOutputService(outputDto);
    }
    @DeleteMapping(value = "/{id}")
    public Result deleteOutput(@PathVariable Integer id){
        return outputService.deleteById(id);
    }

}
