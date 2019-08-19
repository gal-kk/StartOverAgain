package gk.gk;

import gk.gk.Domain.User.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto addUser(UserDto userDto);

    List<UserDto> findAll();

    UserDto findByUserId(String userId);

    UserDto updateUser(UserDto userDto);

    UserDto findByEmail(String userName);
}
