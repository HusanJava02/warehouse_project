package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Category;
import uz.pdp.warehouseproject.payload.CategoryDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.CategoryService;
import uz.pdp.warehouseproject.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto){
      return categoryService.addCategoryService(categoryDto);
    }

    @GetMapping(value = "/{id}")
    public List<Category> getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }
    @GetMapping
    public List<Category> getCategory(@RequestParam(name = "size") Integer size, @RequestParam(name = "page") Integer startPage){
       return categoryService.getAllCategory(startPage,size);
    }
    @PutMapping(value = "{id}")
    public Result editCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        return categoryService.editCategory(id,categoryDto);
    }
    @DeleteMapping
    public Result deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }
}
