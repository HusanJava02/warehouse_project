package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.warehouseproject.entity.Category;
import uz.pdp.warehouseproject.payload.CategoryDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Result addCategoryService(CategoryDto categoryDto) {
        Category category = new Category();
        if (categoryDto.getActive() != null){
            category.setActive(categoryDto.getActive());
        }
        category.setName(categoryDto.getName());
        if (categoryDto.getCategoryId() != null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
            if (optionalCategory.isPresent()){
                Category savingCategory = optionalCategory.get();
                category.setParentCategory(savingCategory);
            }else return new Result(false,"Not given Category with your given id");

        }else{
            category.setParentCategory(null);
        }
        categoryRepository.save(category);
        return new Result(true,"successfully saved");

    }


    public List<Category> getAllCategory(@RequestParam(name = "start") int startPage, @RequestParam("size") int sizePage) {
        //1-1=0     2-1=1    3-1=2    4-1=3
        //select * from student limit 10 offset (0*10)
        //select * from student limit 10 offset (1*10)
        //select * from student limit 10 offset (2*10)
        //select * from student limit 10 offset (3*10)
        Pageable pageable = PageRequest.of(startPage, sizePage);
        Page<Category> pageOfCategory = categoryRepository.findAll(pageable);
        return pageOfCategory.getContent();

    }

    public List<Category> getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return Collections.singletonList(optionalCategory.get());
        }
        return new ArrayList<>();
    }

    public Result deleteCategory(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return new Result(true, "succesfully deleted");
        } else return new Result(false, "category not found");

    }

    public Result editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getCategoryId());
            if (optionalParentCategory.isPresent()) {
                category.setParentCategory(optionalParentCategory.get());
                category.setActive(categoryDto.getActive());
                categoryRepository.save(category);
                return new Result(true, "successfully edited category");
            } else return new Result(false, "Parent category not found");

        } else return new Result(false, "category not found");
    }
}
