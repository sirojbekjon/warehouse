package com.example.warehouse.controller;

import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.WareHouseRepository;
import com.example.warehouse.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WareHouseService wareHouseService;

    @Autowired
    WareHouseRepository wareHouseRepository;

    @PostMapping("/add")
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        Result result = wareHouseService.addWarehouseService(warehouse);
        return result;
    }

    @GetMapping("/get")
    public List<Warehouse> getWarehouseAll(){
        List<Warehouse> warehouseList = wareHouseService.getWarehouseAllService();
        return warehouseList;
    }

    @GetMapping("/get/{id}")
    public Optional<Warehouse> getWarehouseById(@PathVariable Integer id){
        Optional<Warehouse> optionalWarehouse = wareHouseService.getWarehouseByIdService(id);
        return optionalWarehouse;
    }

    @PutMapping("/edit/{id}")
    public Result editWarehouseById(@PathVariable Integer id,@RequestBody Warehouse warehouse){
        Result result = wareHouseService.editWarehouseById(id, warehouse);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteWarehouseById(@PathVariable Integer id){
        Result result = wareHouseService.deleteWarehouseById(id);
        return result;
    }
}
