package com.example.warehouse.controller;

import com.example.warehouse.entity.User;
import com.example.warehouse.payload.Result;
import com.example.warehouse.payload.UserDto;
import com.example.warehouse.repository.WareHouseRepository;
import com.example.warehouse.service.UserService;
import com.example.warehouse.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public Result addUser(@RequestBody UserDto userDto){
        Result result = userService.addUserService(userDto);
        return result;
    }

    @GetMapping("/get")
    public List<User> getUsersAll(){
        return userService.getUsersService();

    }

    @GetMapping("/get/{id}")
    public Optional<User> getUserById(@PathVariable Integer id){
        Optional<User> usersById = userService.getUsersById(id);
        return usersById;
    }

    @PutMapping("/edit/{id}")
    public Result editUserById(@PathVariable Integer id,@RequestBody UserDto userDto){
        Result result = userService.editUserByIdService(id, userDto);
        return result;
    }
    @DeleteMapping("/delete/{id}")
    public Result deleteUserById(@PathVariable Integer id){
        Result result = userService.deleteUserByIdService(id);
        return result;
    }

}
