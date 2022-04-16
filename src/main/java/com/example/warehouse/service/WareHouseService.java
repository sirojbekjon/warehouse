package com.example.warehouse.service;

import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WareHouseService {

    @Autowired
    WareHouseRepository wareHouseRepository;

    public Result addWarehouseService(Warehouse warehouse) {
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName(warehouse.getName());
        Warehouse warehouseSaved = wareHouseRepository.save(warehouse1);
        if (warehouseSaved.getId() != null) {
            return new Result("Warehouse saved", true);
        }
        return new Result("warehouse not saved",false);
    }

    public List<Warehouse> getWarehouseAllService(){
        List<Warehouse> warehouseList = wareHouseRepository.findAll();
        return warehouseList;
    }

    public Optional<Warehouse> getWarehouseByIdService(Integer id){
        Optional<Warehouse> optionalWarehouse = wareHouseRepository.findById(id);
        return optionalWarehouse;
    }

    public Result editWarehouseById(Integer id,Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = wareHouseRepository.findById(id);
        if (optionalWarehouse.isPresent()){
            Warehouse warehouse1 = optionalWarehouse.get();
            warehouse1.setName(warehouse.getName());
            wareHouseRepository.save(warehouse1);
            return new Result("warehouse edited",true);
        }
        return new Result("warehouse not found",false);
    }

    public Result deleteWarehouseById(Integer id){
        wareHouseRepository.deleteById(id);
        Optional<Warehouse> optionalWarehouse = Optional.of(wareHouseRepository.getById(id));
        if (optionalWarehouse.isPresent())
            return new Result("warehouse deleted",true);

        return new Result("warehouse not found",false);
    }
}
