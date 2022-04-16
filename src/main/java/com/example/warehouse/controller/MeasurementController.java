package com.example.warehouse.controller;

import com.example.warehouse.entity.Measurement;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;
    @PostMapping("/add")
    public Result addMeasurementController(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    @GetMapping("/get")
    public List<Measurement> getAllMeasurement(){
        List<Measurement> allMeasurementService = measurementService.getAllMeasurementService();
        return allMeasurementService;
    }

    @GetMapping("/get/{id}")
    public Optional<Measurement> getOneMeasurement(@PathVariable Integer id){
        Optional<Measurement> oneMeasurementService = measurementService.getOneMeasurementService(id);
        return oneMeasurementService;
    }

    @PutMapping("/edit/{id}")
    public Result editMeasurement(@PathVariable Integer id,@RequestBody Measurement measurement){
        Result result = measurementService.editMeasurementService(id, measurement);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        Result result = measurementService.deleteMeasurementService(id);
        return result;
    }
}
