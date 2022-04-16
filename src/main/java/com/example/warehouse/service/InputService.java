package com.example.warehouse.service;

import com.example.warehouse.entity.Currency;
import com.example.warehouse.entity.Input;
import com.example.warehouse.entity.Supplier;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.payload.InputDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.CurrencyRepository;
import com.example.warehouse.repository.InputRepository;
import com.example.warehouse.repository.SupplierRepository;
import com.example.warehouse.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.*;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    WareHouseRepository wareHouseRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addInputService(InputDto inputDto){
        boolean exists = inputRepository.existsByFactureNumberAndCode(inputDto.getFacture_number(), inputDto.getCode());
        if (exists){
            return new Result("this already exists",false);
        }else {
//            Calendar cal = new GregorianCalendar();
//            Date date = cal.getTime();
            Input input1 = new Input();
            input1.setCode(inputDto.getCode());
            input1.setFactureNumber(inputDto.getFacture_number());
            input1.setDate(Timestamp.valueOf(OffsetDateTime.now().toLocalDateTime()));

            Optional<Warehouse> optionalWarehouse = wareHouseRepository.findById(inputDto.getWarehouse_id());
            Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrency_id());
            Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplier_id());
            input1.setWarehouse(optionalWarehouse.get());
            input1.setCurrency(optionalCurrency.get());
            input1.setSupplier(optionalSupplier.get());

            inputRepository.save(input1);
            return new Result("input saved",true);
        }
    }

    public List<Input> getInputsService(){
        Collection<Input> inputs = inputRepository.findAll();
        return (List<Input>) inputs;
    }

    public Optional<Input> getInputByIdService(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput;
    }

    public Result editInputByIdService(Integer id,InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()){
            Input input = optionalInput.get();
            input.setFactureNumber(inputDto.getFacture_number());
            input.setCode(inputDto.getCode());
            input.setDate(Timestamp.valueOf(OffsetDateTime.now().toLocalDateTime()));

            Optional<Warehouse> optionalWarehouse = wareHouseRepository.findById(inputDto.getWarehouse_id());
            Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplier_id());
            Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrency_id());

            input.setWarehouse(optionalWarehouse.get());
            input.setSupplier(optionalSupplier.get());
            input.setCurrency(optionalCurrency.get());

            inputRepository.save(input);
            return new Result("input edited",true);
        }
        return new Result("input not found",false);
    }

    public Result deleteInputByIdService(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()){
            inputRepository.deleteById(id);
            return new Result("input deleted",true);
        }
        return new Result("input not found",false);
    }


}
