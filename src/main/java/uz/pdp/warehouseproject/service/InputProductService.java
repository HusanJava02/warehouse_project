package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.warehouseproject.entity.Input;
import uz.pdp.warehouseproject.entity.InputProduct;
import uz.pdp.warehouseproject.entity.Product;
import uz.pdp.warehouseproject.payload.InputProductDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.InputProductRepository;
import uz.pdp.warehouseproject.repository.InputRepository;
import uz.pdp.warehouseproject.repository.ProductRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InputRepository inputRepository;




    public List<InputProduct> inputProductsWithInputId(@PathVariable Integer inputId){
        List<InputProduct> allByInputId = inputProductRepository.findAllByInputId(inputId);
        return allByInputId;
    }
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){
        InputProduct inputProduct = new InputProduct();
        Integer productId = inputProductDto.getProductId();
        Integer inputId = inputProductDto.getInputId();
        Optional<Input> optionalInput = inputRepository.findById(inputId);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            inputProduct.setProduct(product);
        }else return new Result(false,"not found product with given id");
        if (optionalInput.isPresent()) {
            Input input = optionalInput.get();
            inputProduct.setInput(input);
        }else return new Result(false,"not found input with given id");

        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpiredDate(new Timestamp(inputProductDto.getExpiredDate()));
        inputProductRepository.save(inputProduct);
        return new Result(true,"succesfully saved input product");
    }

    public Result deleteInputProduct(Integer id){
        if (inputRepository.existsById(id)){
            inputRepository.deleteById(id);
            return new Result(true,"succesfully deleted");
        }else return new Result(false,"not found inputProduct");
    }

}
