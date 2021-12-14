package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.InputProduct;
import uz.pdp.warehouseproject.payload.InputProductDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.InputProductService;

import java.util.List;

@RestController
@RequestMapping(value = "/input-product")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    /*
       Input Id beriladi va shu inputda nimalar kirim bolganini korish mumkin
     */
    @GetMapping(value = "/{inputId}")
    public List<InputProduct> getAllInputProducts(@PathVariable Integer inputId) {
        return inputProductService.inputProductsWithInputId(inputId);
    }

    @PostMapping
    public Result addInputProductController(@RequestBody InputProductDto inputProductDto){
       return inputProductService.addInputProduct(inputProductDto);
    }

    @DeleteMapping(value = "/id")
    public Result deleteByid(@PathVariable Integer id){
        return inputProductService.deleteInputProduct(id);
    }


}
