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
}
