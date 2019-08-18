package gk.gk;

import gk.gk.Domain.UserDto;
import gk.gk.Domain.UserRest;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDto userDto);

    List<UserDto> findAll();
}
