package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Client;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.ClientService;

import java.util.List;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @PostMapping
    public Result addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }
    @GetMapping(value = "/{id}")
    public List<Client> getAllClients(@PathVariable Integer id){
        return clientService.getClient(id);
    }
    @DeleteMapping(value = "/{id}")
    public Result deleteClient(@PathVariable Integer id){
        return clientService.deleteClient(id);
    }
    @PutMapping(value = "/id")
    public Result editClient(@RequestBody Client client,@PathVariable Integer id){
        Result result = clientService.editClient(client, id);
        return result;
    }
}
