package com.example.warehouse.service;

import com.example.warehouse.entity.Attachment;
import com.example.warehouse.entity.Category;
import com.example.warehouse.entity.Measurement;
import com.example.warehouse.entity.Product;
import com.example.warehouse.payload.ProductDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.AttachmentRepository;
import com.example.warehouse.repository.CategoryRepository;
import com.example.warehouse.repository.MeasurementRepository;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;


    public Result addProductService(ProductDto productDto){
        boolean exists = productRepository.existsByNameAndCodeAndCategoryId(productDto.getName(), productDto.getCode(), productDto.getCategory_id());
        if (exists)
            return new Result("this product already exists",false);
        else
        {
            Product product1 = new Product();
            product1.setName(productDto.getName());
            product1.setCode(productDto.getCode());

            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory_id());
            Category category = optionalCategory.get();
            product1.setCategory(category);

            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhoto_id());
            Attachment attachment = optionalAttachment.get();
            product1.setPhoto(attachment);

            Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurement_id());
            Measurement measurement = optionalMeasurement.get();
            product1.setMeasurement(measurement);

            productRepository.save(product1);
            return new Result("product saved",true);



        }
    }

    public List<Product> getProductsServices(){
        List<Product> productList = productRepository.findAll();

        return productList;
    }
    public Optional<Product> getProductByIdService(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();
        return Optional.of(product);

    }

    public Result editProductByIdService(Integer id,ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setCode(productDto.getCode());
            Optional<Measurement> measurement = measurementRepository.findById(productDto.getMeasurement_id());
            product.setMeasurement(measurement.get());
            Optional<Category> category = categoryRepository.findById(productDto.getCategory_id());
            product.setCategory(category.get());
            Optional<Attachment> attachment = attachmentRepository.findById(productDto.getPhoto_id());
            product.setPhoto(attachment.get());
            Product editProduct = productRepository.save(product);
            return new Result("product edited",true);
        }
        return new Result("product not found",false);
    }

    public Result deleteProductByIdService(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return new Result("product deleted",true);
        }
        return new Result("product not found",false);
    }

}
