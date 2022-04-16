package com.example.warehouse.controller;

import com.example.warehouse.entity.Supplier;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @PostMapping("/add")
    public Result addSupplier(@RequestBody Supplier supplier){
        Result result = supplierService.addSupplierService(supplier);
        return result;
    }

    @GetMapping("/get")
    public List<Supplier> getSupplier(){
        List<Supplier> suppliers = supplierService.getSupplierService();
        return suppliers;
    }

    @GetMapping("/get/{id}")
    public Optional<Supplier> getSupplierById(@PathVariable Integer id){
        Optional<Supplier> supplier = supplierService.getSupplierServiceById(id);
        return supplier;
    }

    @PutMapping("/edit/{id}")
    public Result editSupplier(@PathVariable Integer id, @RequestBody Supplier supplier){
        Result result = supplierService.editSupplierService(id,supplier);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteSupplier(@PathVariable Integer id){
        Result result = supplierService.deletSupplierService(id);
        return result;
    }
    
}
