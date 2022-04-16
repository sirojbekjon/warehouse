package com.example.warehouse.controller;

import com.example.warehouse.entity.Input;
import com.example.warehouse.payload.InputDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping("/add")
    public Result addInput(@RequestBody InputDto inputDto){
        Result result = inputService.addInputService(inputDto);
        return result;
    }

    @GetMapping("/get")
    public List<Input> getInputs(){
        Collection<Input> inputsService = inputService.getInputsService();
        return (List<Input>) inputsService;
    }

    @GetMapping("/get/{id}")
    public Optional<Input> getInputById(@PathVariable Integer id){
        Optional<Input> optionalInput = inputService.getInputByIdService(id);
        return optionalInput;
    }

    @PutMapping("/edit/{id}")
    public Result editInputById(@PathVariable Integer id, @RequestBody InputDto inputDto){
        Result result = inputService.editInputByIdService(id, inputDto);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteInputById(@PathVariable Integer id){
        Result result = inputService.deleteInputByIdService(id);
        return result;
    }

}
