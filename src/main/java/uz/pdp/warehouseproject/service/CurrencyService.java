package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.repository.CurrencyRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;
    public List<Currency> getAllCurrencyService() {
        return  currencyRepository.findAll();
    }

    public List<Currency> getCurrencyByIdService(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()){
            return Collections.singletonList(optionalCurrency.get());
        }else return Collections.emptyList();
    }

    public Result addCurrencyService(Currency currency) {
        Currency savingCurrency = new Currency();
        if (currency.getActive() != null && currency.getName() != null){
            savingCurrency.setActive(currency.getActive());
            savingCurrency.setName(currency.getName());
            currencyRepository.save(savingCurrency);
            return new Result(true,"succesfully saved");
        }else return new Result(false,"you sent null values");

    }
    public Result editCurrency(Currency currency , Integer id){
        if (currency.getName() != null){
            Optional<Currency> optionalCurrency = currencyRepository.findById(id);
            if (optionalCurrency.isPresent()){
                Currency editingCurrency = optionalCurrency.get();
                editingCurrency.setName(currency.getName());
                System.out.println(currency.getActive());
                if (currency.getActive() != null){
                    editingCurrency.setActive(currency.getActive());
                }
                System.out.println(currency.getActive());
                currencyRepository.save(editingCurrency);
                return new Result(true,"succesfully edited");
            }else
            return new Result(false,"not found currency with given id");
        }else return new Result(false,"you sended null values for name");
    }

    public Result deleteByIdService(Integer id) {
        if (currencyRepository.existsById(id)){
            currencyRepository.deleteById(id);
            return new Result(true,"successfully deleted");
        }else return new Result(false,"not found currency with given id");
    }
}
