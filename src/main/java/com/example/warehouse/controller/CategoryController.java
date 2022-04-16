package com.example.warehouse.controller;

import com.example.warehouse.entity.Category;
import com.example.warehouse.payload.CategoryDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategoryService(categoryDto);
        return result;
    }

    @GetMapping("/getbyid/{id}")
    public Optional<Category> getCategory(@PathVariable Integer id){
       return categoryService.getCategoryService(id);
    }

    @GetMapping("/get")
    public List<Category> getCategories(){
       return categoryService.getCategoriesService();
    }

    //categorylarni bolasini topish
    @PutMapping("/edit/{id}")
    public Result editCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        Result result = categoryService.editCategoryService(categoryDto, id);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        Result result = categoryService.deleteCategoryService(id);
        return result;
    }
}
