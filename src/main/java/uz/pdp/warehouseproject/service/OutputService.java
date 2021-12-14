package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.Client;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.entity.Output;
import uz.pdp.warehouseproject.entity.Warehouse;
import uz.pdp.warehouseproject.payload.OutputDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.ClientRepository;
import uz.pdp.warehouseproject.repository.CurrencyRepository;
import uz.pdp.warehouseproject.repository.OutputRepository;
import uz.pdp.warehouseproject.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ClientRepository clientRepository;
    public List<Output> getAllOutputs(){
        return outputRepository.findAll();
    }

    public Result addOutputService(OutputDto outputDto){
        Output output = new Output();
        Integer clientId = outputDto.getClientId();
        Integer currencyId = outputDto.getCurrencyId();
        Integer warehouseId = outputDto.getWarehouseId();
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()){
            Client client = optionalClient.get();
            output.setClient(client);
        }else return new Result(false,"client not found");
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
        if (optionalWarehouse.isPresent()){
            Warehouse warehouse = optionalWarehouse.get();
            output.setWarehouse(warehouse);
        }else return new Result(false,"warehouse not found with given id");
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if (optionalCurrency.isPresent()){
            Currency currency = optionalCurrency.get();
            output.setCurrency(currency);
        }else return new Result(false,"currency not found");
        output.setCode(outputDto.getCode());
        output.setDate(new Timestamp(outputDto.getDate()));
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);
        return new Result(true,"succesfuully saved");
    }


    public Result deleteById(Integer id) {
        if (outputRepository.existsById(id)){
            outputRepository.deleteById(id);
            return new Result(true,"succesfully deleted");
        }else return new Result(true,"not found Output");
    }
}
