package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.Currency;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAllCurrency(){
        return currencyService.getAllCurrencyService();
    }

    @GetMapping(value = "/{id}")
    public List<Currency> getCurrencyById(@PathVariable Integer id){
       return currencyService.getCurrencyByIdService(id);
    }

    @PostMapping
    public Result addCurrency(@RequestBody Currency currency){
        Result result = currencyService.addCurrencyService(currency);
        return result;
    }
    @DeleteMapping(value = "/{id}")
    public Result deleteCurrency(@PathVariable Integer id){
        Result result = currencyService.deleteByIdService(id);
        return result;
    }

    @PutMapping(value = "/{id}")
    public Result editCurrency(@PathVariable Integer id, @RequestBody Currency currency){
        return currencyService.editCurrency(currency,id);
    }
}
