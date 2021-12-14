package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.entity.Input;
import uz.pdp.warehouseproject.entity.Warehouse;
import uz.pdp.warehouseproject.payload.InputDto;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.CurrencyRepository;
import uz.pdp.warehouseproject.repository.InputRepository;
import uz.pdp.warehouseproject.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public List<Input> getAllInputs(){
        return inputRepository.findAll();
    }

    public Result addInput(InputDto inputDto){
        Input input = new Input();
        Integer warehouseId = inputDto.getWarehouseId();
        Integer currencyId = inputDto.getCurrencyId();
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
        if (optionalWarehouse.isPresent()){
            Warehouse warehouse = optionalWarehouse.get();
            input.setWarehouse(warehouse);
        }else return new Result(false,"not found warehouse");

        if (optionalCurrency.isPresent()){
            Currency currency = optionalCurrency.get();
            input.setCurrency(currency);
        }else return new Result(false,"not found currency");
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(UUID.randomUUID().toString());
        input.setDate(new Timestamp(inputDto.getInputDate()));
        inputRepository.save(input);
        return new Result(true,"succesfully saved input");
    }

    // TODO: 12/14/2021
    public Result editInput(InputDto inputDto){
       return null;
    }

    public Result deleteById(Integer id) {
        inputRepository.deleteById(id);
        return new Result(true,"successfully");
    }


}
