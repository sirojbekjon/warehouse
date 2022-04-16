package com.example.warehouse.controller;

import com.example.warehouse.entity.Output;
import com.example.warehouse.payload.OutputDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping("/add")
    public Result addOutput(@RequestBody OutputDto outputDto){
        Result result = outputService.addOutputService(outputDto);
        return result;
    }

    @GetMapping("/get")
    public List<Output> getOutputs(){
        Collection<Output> outputsService = outputService.getOutputsService();
        return (List<Output>) outputsService;
    }

    @GetMapping("/get/{id}")
    public Optional<Output> getOutputById(@PathVariable Integer id){
        Optional<Output> optionalOutput = outputService.getOutputByIdService(id);
        return optionalOutput;
    }

    @PutMapping("/edit/{id}")
    public Result editOutputById(@PathVariable Integer id, @RequestBody OutputDto outputDto){
        Result result = outputService.editOutputByIdService(id,outputDto);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteOutputById(@PathVariable Integer id){
        Result result = outputService.deleteOutputByIdService(id);
        return result;
    }
}
