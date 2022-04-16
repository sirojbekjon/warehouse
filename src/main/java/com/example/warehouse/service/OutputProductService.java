package com.example.warehouse.service;

import com.example.warehouse.entity.Input;
import com.example.warehouse.entity.Output;
import com.example.warehouse.entity.OutputProduct;
import com.example.warehouse.entity.Product;
import com.example.warehouse.payload.OutputProductDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.*;
import com.example.warehouse.repository.OutputProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutputRepository outputRepository;

    public Result addOutputProductService(OutputProductDto outputProductDto){
        boolean exists = outputProductRepository.existsByProductIdAndInputId(outputProductDto.getProduct_id(),outputProductDto.getOutput_id());

        if (exists){
            return new Result("this already exists",false);
        }else {
            OutputProduct outputProduct = new OutputProduct();

            outputProduct.setAmount(outputProductDto.getAmount());
            outputProduct.setPrice(outputProductDto.getPrice());

            Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProduct_id());
            Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutput_id());

            outputProduct.setProduct(optionalProduct.get());
            outputProduct.setOutput(optionalOutput.get());

            outputProductRepository.save(outputProduct);
            return new Result("outputProduct saved",true);

        }
    }

    public List<OutputProduct> getoutputProductService(){
        Collection<OutputProduct> outputProducts = outputProductRepository.findAll();
        return (List<OutputProduct>) outputProducts;
    }

    public Optional<OutputProduct> getOutputProductByIdService(Integer id){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()){
            OutputProduct outputProduct = optionalOutputProduct.get();
            return Optional.of(outputProduct);
        }
        return null;
    }

    public Result editOutputProductByIdService(Integer id, OutputProductDto outputProductDto){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()){
            OutputProduct outputProduct = optionalOutputProduct.get();
            outputProduct.setAmount(outputProductDto.getAmount());
            outputProduct.setPrice(outputProductDto.getPrice());


            Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProduct_id());
            Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutput_id());

            outputProduct.setProduct(optionalProduct.get());
            outputProduct.setOutput(optionalOutput.get());

            outputProductRepository.save(outputProduct);
            return new Result("outputProduct edited",true);



        }
        return new Result("outputProduct not found",false);
    }

    public Result deleteOutputProductByIdService(Integer id){
        outputProductRepository.deleteById(id);
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (optionalOutputProduct.isPresent()){
            return new Result("outputProduct undeleted",false);
        }return new Result("outputProduct deleted",true);
    }



}
