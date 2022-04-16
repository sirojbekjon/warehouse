package com.example.warehouse.service;

import com.example.warehouse.entity.Currency;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrencyService(Currency currency){
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName){
            return new Result("this currency already exists",false);
        }
        else {
            Currency currency1 = new Currency();
            currency1.setName(currency.getName());
            currencyRepository.save(currency1);
            return new Result("currency saved",true);
        }
    }

    public List<Currency> getCurrenciesService(){
        List<Currency> currencies = currencyRepository.findAll();
        return currencies;
    }

    public Optional<Currency> getCurrency(Integer id){
        boolean exists = currencyRepository.existsById(id);
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (exists)
            return currencyOptional;
        return null;
    }

    public Result editCurrencyService(Integer id,Currency currency){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()){
            Currency currency1 = optionalCurrency.get();
            currency1.setName(currency.getName());
            currencyRepository.save(currency1);
            return new Result("currency edeted",true);
        }return new Result("currency not found",false);
    }

    public Result deleteCurrencyService(Integer id){
        Result result = new Result();
        if (!currencyRepository.findById(id).isPresent()){
            result = new Result("currency not found",false);
        }else{
        currencyRepository.deleteById(id);
        if (!currencyRepository.findById(id).isPresent())
        result = new Result("currency deleted",true);
        }
        return result;
    }

}
