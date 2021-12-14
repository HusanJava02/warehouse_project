package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.warehouseproject.entity.Attachment;
import uz.pdp.warehouseproject.entity.Category;
import uz.pdp.warehouseproject.entity.Measurement;
import uz.pdp.warehouseproject.entity.Product;
import uz.pdp.warehouseproject.payload.ProductDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.AttachmentRepository;
import uz.pdp.warehouseproject.repository.CategoryRepository;
import uz.pdp.warehouseproject.repository.MeasurementRepository;
import uz.pdp.warehouseproject.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    ProductRepository productRepository;

    public Result addProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        Integer attachmentId = productDto.getAttachmentId();
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            product.setAttachment(attachment);
        } else return new Result(false, "error cannot found attachment");
        if (productDto.getCategoryId() == null) {
            product.setCategory(null);
        } else {

            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                product.setCategory(category);
            } else return new Result(false, "category not found");
        }
        Integer measurementId = productDto.getMeasurementId();
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (optionalMeasurement.isPresent()) {
            Measurement measurement = optionalMeasurement.get();
            product.setMeasurement(measurement);
        } else return new Result(false, "measurement not found");

        product.setCode(UUID.randomUUID().toString());
        if (productDto.getActive() != null) {
            product.setActive(productDto.getActive());
        }
        product.setName(productDto.getName());
        productRepository.save(product);
        return new Result(true, "successfully saved Product");
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Result deleteById(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return new Result(true, "deleted product");
        } else return new Result(false, "not found product");
    }

    public Result editProduct(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result(false, "product not found with given id");
        Product product = optionalProduct.get();
        Integer categoryId = productDto.getCategoryId();
        Integer attachmentId = productDto.getAttachmentId();
        Integer measurementId = productDto.getMeasurementId();
        categoryRepository.findById(categoryId);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(attachmentId);
        if (!optionalAttachment.isPresent())
            return new Result(false, "attachment not found");
        Attachment attachment = optionalAttachment.get();
        product.setAttachment(attachment);
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (!optionalCategory.isPresent())
            return new Result(false, "not found category");

        Category category = optionalCategory.get();
        product.setCategory(category);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(measurementId);
        if (!optionalMeasurement.isPresent())
            return new Result(false, "not found measurement");
        Measurement measurement = optionalMeasurement.get();
        product.setMeasurement(measurement);
        product.setName(productDto.getName());
        if (productDto.getActive() != null)
            product.setActive(productDto.getActive());
        productRepository.save(product);
        return new Result(true, "successfully edited");
    }
}
