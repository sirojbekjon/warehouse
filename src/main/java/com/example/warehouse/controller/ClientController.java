package com.example.warehouse.controller;

import com.example.warehouse.entity.Client;
import com.example.warehouse.payload.Result;
import com.example.warehouse.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/add")
    public Result addClient(@RequestBody Client client){
        Result result = clientService.addClientService(client);
        return result;
    }

    @GetMapping("/get")
    public List<Client> getClient(){
        List<Client> clients = clientService.getClientService();
        return clients;
    }

    @GetMapping("/get/{id}")
    public Optional<Client> getClientById(@PathVariable Integer id){
        Optional<Client> clients = clientService.getClientServiceById(id);
        return clients;
    }

    @PutMapping("/edit/{id}")
    public Result editClient(@PathVariable Integer id, @RequestBody Client client){
        Result result = clientService.editClientService(id,client);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteClient(@PathVariable Integer id){
        Result result = clientService.deletClientService(id);
        return result;
    }

}
