package com.example.warehouse.service;

import com.example.warehouse.entity.*;
import com.example.warehouse.payload.OutputDto;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WareHouseRepository wareHouseRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ClientRepository clientRepository;

    public Result addOutputService(OutputDto outputDto){
        boolean exists = outputRepository.existsByFactureNumberAndCode(outputDto.getFacture_number(), outputDto.getCode());
        if (exists){
            return new Result("this already exists",false);
        }else {
//            Calendar cal = new GregorianCalendar();
//            Date date = cal.getTime();
            Output output1 = new Output();
            output1.setCode(outputDto.getCode());
            output1.setFactureNumber(outputDto.getFacture_number());
            output1.setDate(Timestamp.valueOf(OffsetDateTime.now().toLocalDateTime()));
            Optional<Warehouse> optionalWarehouse = wareHouseRepository.findById(outputDto.getWarehouse_id());
            Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrency_id());
            Optional<Client> optionalClient = clientRepository.findById(outputDto.getClient_id());
            output1.setWarehouse(optionalWarehouse.get());
            output1.setClient(optionalClient.get());
            output1.setCurrency(optionalCurrency.get());
            outputRepository.save(output1);
            return new Result("output saved",true);
        }
    }

    public List<Output> getOutputsService(){
        return outputRepository.findAll();


    }

    public Optional<Output> getOutputByIdService(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput;
    }

    public Result editOutputByIdService(Integer id,OutputDto outputDto){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()){
            Output output = optionalOutput.get();
            output.setFactureNumber(outputDto.getFacture_number());
            output.setCode(outputDto.getCode());
            output.setDate(Timestamp.valueOf(OffsetDateTime.now().toLocalDateTime()));

            Optional<Warehouse> optionalWarehouse = wareHouseRepository.findById(outputDto.getWarehouse_id());
            Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrency_id());
            Optional<Client> optionalClient = clientRepository.findById(outputDto.getClient_id());

            output.setWarehouse(optionalWarehouse.get());
            output.setCurrency(optionalCurrency.get());
            output.setClient(optionalClient.get());
            outputRepository.save(output);
            return new Result("output edited",true);
        }
        return new Result("output not found",false);
    }

    public Result deleteOutputByIdService(Integer id){
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()){
            outputRepository.deleteById(id);
            return new Result("output deleted",true);
        }
        return new Result("output not found",false);
    }


}
