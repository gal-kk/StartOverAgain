package gk.gk;

import gk.gk.Domain.Address.AddressDto;
import gk.gk.Domain.Address.AddressRequestModel;
import gk.gk.Domain.Address.AddressService;
import gk.gk.Domain.User.UserDetailRequestModel;
import gk.gk.Domain.User.UserDto;
import gk.gk.Domain.User.UserRest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    Utils utils;

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<UserRest> ProcessGet() {
        List<UserDto> usersDto = userService.findAll();

        Type listType = new TypeToken<List<UserRest>>() {}.getType();
        List<UserRest> list = modelMapper.map(usersDto, listType);
        return list;
    }


    @PostMapping
    public UserRest ProcessPost(@RequestBody UserDetailRequestModel userDetail) {

        UserDto userDto = modelMapper.map(userDetail, UserDto.class);

        UserDto re = userService.addUser(userDto);
        return modelMapper.map(re, UserRest.class);
    }

    @PatchMapping(path = "/{userId}")
    public UserRest ProcessUpdate(@PathVariable String userId, @RequestBody UserDetailRequestModel userDetail){
        UserDto userDto = userService.findByUserId(userId);
        if(userDetail.getFirstName()!=null){
            userDto.setFirstName(userDetail.getFirstName());
        }
        if(userDetail.getLastName()!=null){
            userDto.setLastName(userDetail.getLastName());
        }
        if(userDetail.getEmail()!=null){
            userDto.setEmail(userDetail.getEmail());
        }
        if(userDetail.getPassword()!=null){
            userDto.setPassword(userDetail.getPassword());
        }
        if(userDetail.getAddresses()!=null){
            List<AddressDto> addresses = new ArrayList<>();
            for(int i=0; i< userDetail.getAddresses().size();i++){
                AddressRequestModel addressRequestModel = userDetail.getAddresses().get(i);
                AddressDto temp = modelMapper.map(addressRequestModel, AddressDto.class);
                temp.setUserBelongTo(userDto);
                temp.setAddressId(utils.AddressIdGen(10));
                addresses.add(temp);
            }
            userDto.setAddresses(addresses);
            addressService.purgeByUserDetail(userDto);
        }

        UserDto re = userService.updateUser(userDto);
        return modelMapper.map(re, UserRest.class);
    }
}
