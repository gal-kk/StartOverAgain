package gk.gk;

import gk.gk.Domain.UserDetailRequestModel;
import gk.gk.Domain.UserDto;
import gk.gk.Domain.UserEntity;
import gk.gk.Domain.UserRest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
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

    ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public List<UserRest> ProcessGet() {
        List<UserRest> users = new ArrayList<>();
        List<UserDto> usersDto = userService.findAll();

        Type listType = new TypeToken<List<UserRest>>() {}.getType();
        List<UserRest> list = modelMapper.map(usersDto, listType);
        return list;
    }


    @PostMapping
    public UserRest ProcessPost(@RequestBody UserDetailRequestModel userDetail) {

        UserRest userRest = new UserRest();
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

        UserDto re = userService.updateUser(userDto);
        return modelMapper.map(re, UserRest.class);
    }
}
