package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.Client;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClient(Client client) {
        clientRepository.save(client);
        return new Result(true, "succesfully saved");
    }

    public List<Client> getClient(Integer id) {
        return clientRepository.findAll();
    }

    public Result editClient(Client client, Integer id) {
        Optional<Client> optional = clientRepository.findById(id);
        if (optional.isPresent()) {
            Client editingClient = optional.get();
            editingClient.setName(client.getName());
            editingClient.setPhoneNumber(client.getPhoneNumber());
            clientRepository.save(editingClient);
            return new Result(true, "succesfully edited");
        } else return new Result(false, "not found with given id");
    }

    public Result deleteClient(Integer id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return new Result(true, "deleted");
        } else return new Result(false, "not found");
    }


}
