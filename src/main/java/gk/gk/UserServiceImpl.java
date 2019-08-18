package gk.gk;

import gk.gk.Domain.Address.AddressDto;
import gk.gk.Domain.UserDto;
import gk.gk.Domain.UserEntity;
import gk.gk.Domain.UserRest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto addUser(UserDto userDto) {
        for (int i = 0; i < userDto.getAddresses().size(); i++) {
            AddressDto temp = userDto.getAddresses().get(i);
            temp.setAddressId(utils.AddressIdGen(10));
            temp.setUserBelongTo(userDto);
            userDto.getAddresses().set(i, temp);
        }
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        userEntity.setEncryptedPassword("241234");
        userEntity.setUserId(utils.UserIdGen(10));
        UserEntity re = userRepository.save(userEntity);

        return modelMapper.map(re, UserDto.class);

    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> reList = new ArrayList<>();
        userRepository.findAll().forEach(i -> reList.add(i));

        Type listType = new TypeToken<List<UserEntity>>() {}.getType();
        List<UserDto> list = modelMapper.map(reList, listType);
        return list;
    }

    @Override
    public UserDto findByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity==null) throw new RuntimeException(userId);


        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        userEntity.setEncryptedPassword("241234");
        UserEntity re = userRepository.save(userEntity);

        return modelMapper.map(re, UserDto.class);
    }
}
