package com.example.warehouse.service;
import com.example.warehouse.entity.Measurement;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;
    public Result addMeasurementService(Measurement measurement){
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName)            {
            return new Result("Bunday o'lchov birligi mavjud",false);
        }
        else{
        measurementRepository.save(measurement);
        return new Result("add success",true);
        }
    }
}
