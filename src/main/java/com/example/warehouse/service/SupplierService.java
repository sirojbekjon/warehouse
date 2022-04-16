package com.example.warehouse.service;

import com.example.warehouse.entity.Supplier;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.SupplierRepository;
import com.example.warehouse.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplierService(Supplier supplier){
        boolean existsByName = supplierRepository.existsByName(supplier.getName());
        if (existsByName){
            return new Result("Supplier already exists",false);
        }else{
            Supplier supplier1 = new Supplier();
            supplier1.setName(supplier.getName());
            supplier1.setPhoneNumber(supplier.getPhoneNumber());
            supplierRepository.save(supplier1);
            return new Result("Supplier added",true);
        }
    }

    public List<Supplier> getSupplierService(){
        return supplierRepository.findAll();
    }

    public  Optional<Supplier> getSupplierServiceById(Integer id){
        return supplierRepository.findById(id);
    }

    public Result editSupplierService(Integer id, Supplier Supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()){
            Supplier Supplier1 = optionalSupplier.get();
            Supplier1.setName(Supplier.getName());
            Supplier1.setPhoneNumber(Supplier.getPhoneNumber());
            supplierRepository.save(Supplier1);
            return new Result("Supplier edeted successfully",true);
        }
        return new Result("Supplier not found",false);
    }

    public Result deletSupplierService(Integer id){
        boolean existsById = supplierRepository.existsById(id);
        if (existsById) {
            supplierRepository.deleteById(id);
            return new Result("Supplier deleted", true);
        }else {
            return new Result("Supplier not found",false);
        }
    }
}
