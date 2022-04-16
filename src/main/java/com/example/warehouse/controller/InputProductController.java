package com.example.warehouse.controller;


import com.example.warehouse.entity.InputProduct;
import com.example.warehouse.payload.InputProductDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @PostMapping("/add")
    public Result addInputProduct(@RequestBody InputProductDto inputProductDto){
        Result result = inputProductService.addInputProductService(inputProductDto);
        return result;
    }

    @GetMapping("/get")
    public List<InputProduct> getInputProducts(){
        Collection<InputProduct> inputProductService = this.inputProductService.getInputProductService();
        return (List<InputProduct>) inputProductService;
    }

    @GetMapping("/get/{id}")
    public Optional<InputProduct> getInputProductById(@PathVariable Integer id){
        Optional<InputProduct> inputProductByIdService = inputProductService.getInputProductByIdService(id);
        return inputProductByIdService;
    }

    @PutMapping("/edit/{id}")
    public Result editInputProductById(@PathVariable Integer id,@RequestBody InputProductDto inputProductDto){
        Result result = inputProductService.editInputProductByIdService(id, inputProductDto);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteInputProductById(@PathVariable Integer id){
        Result result = inputProductService.deleteInputProductByIdService(id);
        return result;
    }
}
