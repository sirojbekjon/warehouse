package com.example.warehouse.service;

import com.example.warehouse.entity.Input;
import com.example.warehouse.entity.InputProduct;
import com.example.warehouse.entity.Product;
import com.example.warehouse.payload.InputDto;
import com.example.warehouse.payload.InputProductDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.InputProductRepository;
import com.example.warehouse.repository.InputRepository;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Collection;
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

    public Result addInputProductService(InputProductDto inputProductDto){
        boolean exists = inputProductRepository.existsByProductIdAndExpireDateAndInputId(inputProductDto.getProduct_id(), inputProductDto.getExpire_date(), inputProductDto.getInput_id());

        if (exists){
            return new Result("this already exists",false);
        }else {
            InputProduct inputProduct = new InputProduct();

            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpireDate(Timestamp.valueOf(OffsetDateTime.now().toLocalDateTime()));

            Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProduct_id());
            Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInput_id());

            inputProduct.setProduct(optionalProduct.get());
            inputProduct.setInput(optionalInput.get());

            inputProductRepository.save(inputProduct);
            return new Result("inputProduct saved",true);

        }
    }

    public List<InputProduct> getInputProductService(){
        Collection<InputProduct> inputProducts = inputProductRepository.findAll();
        return (List<InputProduct>) inputProducts;
    }

    public Optional<InputProduct> getInputProductByIdService(Integer id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()){
            InputProduct inputProduct = optionalInputProduct.get();
            return Optional.of(inputProduct);
        }
        return null;
    }

    public Result editInputProductByIdService(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()){

            InputProduct inputProduct = optionalInputProduct.get();
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpireDate(Timestamp.valueOf(OffsetDateTime.now().toLocalDateTime()));

            Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProduct_id());
            Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInput_id());

            inputProduct.setProduct(optionalProduct.get());
            inputProduct.setInput(optionalInput.get());

            inputProductRepository.save(inputProduct);
            return new Result("inputProduct edited",true);



        }
        return new Result("inputProduct not found",false);
    }

    public Result deleteInputProductByIdService(Integer id){
        inputProductRepository.deleteById(id);
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isPresent()){
            return new Result("inputProduct undeleted",false);
        }return new Result("inputProduct deleted",true);
    }



}
