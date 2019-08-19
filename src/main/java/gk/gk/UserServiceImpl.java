package gk.gk;

import gk.gk.Domain.Address.AddressDto;
import gk.gk.Domain.Address.AddressRepository;
import gk.gk.Domain.User.UserDto;
import gk.gk.Domain.User.UserEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
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

    @Override
    public UserDto findByEmail(String userName) {
        UserEntity userEntity = userRepository.findByEmail(userName);
        if(userEntity==null) throw new UsernameNotFoundException(userName);
        return modelMapper.map(userEntity, UserDto.class);

    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(s);
        if (userEntity==null) throw new UsernameNotFoundException(s);


        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
