package com.example.warehouse.service;

import com.example.warehouse.entity.Client;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClientService(Client client){
            boolean existsByName = clientRepository.existsByName(client.getName());
            if (existsByName){
                return new Result("Client already exists",false);
            }else{
                Client client1 = new Client();
                client1.setName(client.getName());
                client1.setPhoneNumber(client.getPhoneNumber());
                clientRepository.save(client1);
                return new Result("Client added",true);
            }
        }

    public List<Client> getClientService(){
        return clientRepository.findAll();
    }

    public Optional<Client> getClientServiceById(Integer id){
        return clientRepository.findById(id);
    }

    public Result editClientService(Integer id,Client client){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()){
            Client client1 = optionalClient.get();
            client1.setName(client.getName());
            client1.setPhoneNumber(client.getPhoneNumber());
            clientRepository.save(client1);
        return new Result("Client edeted successfully",true);
        }
        return new Result("Client not found",false);
    }

    public Result deletClientService(Integer id){
        boolean existsById = clientRepository.existsById(id);
        if (existsById) {
            clientRepository.deleteById(id);
            return new Result("Client deleted", true);
        }else {
            return new Result("Client not found",false);
        }
    }
}
