package com.example.warehouse.controller;


import com.example.warehouse.entity.OutputProduct;
import com.example.warehouse.payload.OutputProductDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductService outputProductService;

    @PostMapping("/add")
    public Result addoutputProduct(@RequestBody OutputProductDto outputProductDto){
        Result result = outputProductService.addOutputProductService(outputProductDto);
        return result;
    }

    @GetMapping("/get")
    public List<OutputProduct> getoutputProducts(){
        Collection<OutputProduct> outputProductService = this.outputProductService.getoutputProductService();
        return (List<OutputProduct>) outputProductService;
    }

    @GetMapping("/get/{id}")
    public Optional<OutputProduct> getOutputProductById(@PathVariable Integer id){
        Optional<OutputProduct> outputProductByIdService = outputProductService.getOutputProductByIdService(id);
        return outputProductByIdService;
    }

    @PutMapping("/edit/{id}")
    public Result editOutputProductById(@PathVariable Integer id,@RequestBody OutputProductDto outputProductDto){
        Result result = outputProductService.editOutputProductByIdService(id, outputProductDto);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteOutputProductById(@PathVariable Integer id){
        Result result = outputProductService.deleteOutputProductByIdService(id);
        return result;
    }
}
