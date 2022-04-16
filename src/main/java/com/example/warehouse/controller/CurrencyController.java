package com.example.warehouse.controller;

import com.example.warehouse.entity.Currency;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping("/add")
    public Result addCurrency(@RequestBody Currency currency){
        Result result = currencyService.addCurrencyService(currency);
        return result;
    }

    @GetMapping("/get")
    public List<Currency> getCurrencies(){
        List<Currency> currencyList = currencyService.getCurrenciesService();
        return currencyList;
    }

    @GetMapping("/get/{id}")
    public Optional<Currency> getCurrency(@PathVariable Integer id){
        Optional<Currency> currencyOptional = currencyService.getCurrency(id);

        return currencyOptional;
    }

    @PutMapping("/edit/{id}")
    public Result edittCurrency(@PathVariable Integer id,@RequestBody Currency currency){
        Result result = currencyService.editCurrencyService(id, currency);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteCurrency(@PathVariable Integer id){
        Result result = currencyService.deleteCurrencyService(id);
        return result;
    }



}
