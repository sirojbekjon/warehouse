package com.example.warehouse.service;

import com.example.warehouse.entity.User;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.payload.Result;
import com.example.warehouse.payload.UserDto;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;

    public Result addUserService(UserDto userDto){

       boolean exists = userRepository.existsByFirstNameAndLastNameAndPhoneNumber(userDto.getFirstName(), userDto.getLastName(), userDto.getPhoneNumber());

        if (exists){
            return new Result("User already exists",false);
        }
        else{
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setCode(userDto.getCode());
            user.setPassword(userDto.getPassword());

            Integer[] getWarehouses_ids = userDto.getWarehouses_ids();
            Set<Warehouse> warehouseSet = new HashSet<>();
            for (int i = 0; i <getWarehouses_ids.length; i++) {
            Optional<Warehouse> warehouseOptional = wareHouseRepository.findById(getWarehouses_ids[i]);
            if (warehouseOptional.isPresent()){
                Warehouse warehouse = warehouseOptional.get();
                warehouseSet.add(warehouse);
                }
            }
            user.setWarehouses(warehouseSet);
            userRepository.save(user);

            return new Result("user saved",true);
        }


    }

    public List<User> getUsersService(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public Optional<User> getUsersById(Integer id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent())
            return userOptional;
        return null;
    }

    public Result editUserByIdService(Integer id,UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            User user1 = optionalUser.get();
            user1.setFirstName(userDto.getFirstName());
            user1.setLastName(userDto.getLastName());
            user1.setPassword(userDto.getPassword());
            user1.setCode(userDto.getCode());
            user1.setPhoneNumber(userDto.getPhoneNumber());


            Integer[] warehouses_ids = userDto.getWarehouses_ids();
            Set<Warehouse> warehouseSet = new HashSet<>();
            for (int i = 0; i < warehouses_ids.length; i++) {
                Optional<Warehouse> optionalWarehouse = wareHouseRepository.findById(warehouses_ids[i]);
                if(optionalWarehouse.isPresent()){
                    warehouseSet.add(optionalWarehouse.get());
                }
            }
            user1.setWarehouses(warehouseSet);
            userRepository.save(user1);
            return new Result("user edit successfully",true);

        }return new Result("user not found",false);

    }

        Result result;
    public Result deleteUserByIdService(Integer id){
        if (!userRepository.findById(id).isPresent()) {
             result = new Result("user not found", false);
        }else {
            userRepository.deleteById(id);
        if (!userRepository.findById(id).isPresent()) {
             result = new Result("user deleted", true);
            }
        }

        return result;
    }

}
