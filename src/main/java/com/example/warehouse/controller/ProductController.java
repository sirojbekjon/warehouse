package com.example.warehouse.controller;

import com.example.warehouse.entity.Product;
import com.example.warehouse.payload.ProductDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/add")
    public Result addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProductService(productDto);
        return result;
    }

    @GetMapping("/get")
    public List<Product> getProducts(){
        return productService.getProductsServices();
    }

    @GetMapping("/get/{id}")
    public Optional<Product> getProductById(@PathVariable Integer id){
        Optional<Product> productByIdService = productService.getProductByIdService(id);

        return productByIdService;
    }

    @PutMapping("/edit/{id}")
    public Result editProductById(@PathVariable Integer id,@RequestBody ProductDto productDto){
        Result result = productService.editProductByIdService(id, productDto);
        return result;
    }


    @DeleteMapping("/delete/{id}")
    public Result deleteProductById(@PathVariable Integer id){
        Result result = productService.deleteProductByIdService(id);
        return result;
    }


}
