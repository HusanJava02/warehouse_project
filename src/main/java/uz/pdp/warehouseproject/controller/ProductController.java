package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Attachment;
import uz.pdp.warehouseproject.entity.Category;
import uz.pdp.warehouseproject.entity.Product;
import uz.pdp.warehouseproject.payload.ProductDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.AttachmentContentRepository;
import uz.pdp.warehouseproject.repository.AttachmentRepository;
import uz.pdp.warehouseproject.repository.CategoryRepository;
import uz.pdp.warehouseproject.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        return productService.deleteById(id);
    }
    @PutMapping(value = "/{id}")
    public Result editProductController(@PathVariable Integer id,@RequestBody ProductDto productDto){
        return productService.editProduct(id,productDto);
    }
    
}
