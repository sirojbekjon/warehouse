package com.example.warehouse.service;
import com.example.warehouse.entity.Measurement;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Measurement> getAllMeasurementService(){
        return measurementRepository.findAll();
    }
    public Optional<Measurement> getOneMeasurementService(Integer id){
        return measurementRepository.findById(id);
    }

    public Result editMeasurementService(Integer id,Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            Measurement measurement1 = optionalMeasurement.get();
            measurement1.setName(measurement.getName());
            measurementRepository.save(measurement1);
            return new Result("measurement edited",true);
        }
        return new Result("measurement not found",false);
    }

    public Result deleteMeasurementService(Integer id){
        measurementRepository.deleteById(id);
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId!=null){
            return new Result("measurement deleted",true);
        }else return new Result("not found",false);
    }
}
