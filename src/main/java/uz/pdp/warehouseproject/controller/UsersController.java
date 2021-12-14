package uz.pdp.warehouseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouseproject.entity.User;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.payload.UserDto;
import uz.pdp.warehouseproject.service.UsersService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UsersController {
    @Autowired
    UsersService usersService;

    @GetMapping
    public List<User> getAllUSers(){
        return usersService.getAllUsers();
    }
    @PostMapping
    public Result addUsersController(@RequestBody UserDto userDto){
        return usersService.addUsers(userDto);
    }
    @PutMapping(value = "/{id}")
    public Result editUsersController(@PathVariable Integer id,@RequestBody UserDto userDto){
        return usersService.editUser(id,userDto);
    }
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable Integer id){
       return usersService.deleteUserService(id);
    }

}
