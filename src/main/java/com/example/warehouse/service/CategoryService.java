package com.example.warehouse.service;

import com.example.warehouse.entity.Category;
import com.example.warehouse.payload.CategoryDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategoryService(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParrentCategoryId()!=null){
            Optional<Category> optionalParrent = categoryRepository.findById(categoryDto.getParrentCategoryId());
            if (!optionalParrent.isPresent())
                return new Result("Category not found",false);
            category.setParrentCategory(optionalParrent.get());
        }
        categoryRepository.save(category);
        return new Result("Category saved successfully",true);
    }

    public Optional<Category> getCategoryService(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
            return optionalCategory;
    }

    public List<Category> getCategoriesService() {
        List<Category> categories = categoryRepository.findAll();
      return categories;
    }

    public Result editCategoryService(CategoryDto categoryDto,Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());

            Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getParrentCategoryId());
            Category category1 = categoryOptional.get();
            category.setParrentCategory(category1);
            categoryRepository.save(category);
            return new Result("Category edited successfully",true);
        }
        return new Result("Category not found",false);

    }

    public Result deleteCategoryService(Integer id){
        categoryRepository.deleteCategoryByParrentCategoryId(id);
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent())
            return new Result("Category deleted",true);
        return new Result("Category don't deleted",false);
    }

}
