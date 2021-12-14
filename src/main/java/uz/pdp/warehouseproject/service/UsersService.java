package uz.pdp.warehouseproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.entity.User;
import uz.pdp.warehouseproject.entity.Warehouse;
import uz.pdp.warehouseproject.payload.Result;
import uz.pdp.warehouseproject.payload.UserDto;
import uz.pdp.warehouseproject.repository.UserRepository;
import uz.pdp.warehouseproject.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Result addUsers(UserDto userDto) {
        List<Warehouse> warehouses = warehouseRepository.findAllById(userDto.getWarehouseId());
        User user = new User(null, userDto.getFirstName(), userDto.getLastName(), userDto.getPhoneNumber(), userDto.getCode(), userDto.getPassword(), userDto.getActive(), warehouses);
        userRepository.save(user);
        return new Result(true, "successfully saved");
    }


    public Result editUser(Integer userId, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userDto.getFirstName());
            user.setPassword(userDto.getPassword());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setCode(userDto.getCode());
            user.setActive(userDto.getActive());
            List<Warehouse> warehouses = warehouseRepository.findAllById(userDto.getWarehouseId());
            user.setWarehouses(warehouses);
            return new Result(true,"successfully edited");
        }
        else return new Result(false,"user not found");
    }

    public Result deleteUserService(Integer id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return new Result(true,"succesfully deleted");
        }else return new Result(false,"not found with given id");
    }
}
