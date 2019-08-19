package gk.gk.Domain.Address;

import gk.gk.Domain.User.UserDto;
import gk.gk.Domain.User.UserEntity;
import gk.gk.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void purgeByUserDetail(UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());
        List<AddressEntity> addresses = userEntity.getAddresses();
        for(int i =  0;i<addresses.size(); i++){
            AddressEntity temp = addresses.get(i);
            addressRepository.deleteByAddressId(temp.getAddressId());
        }


//        for(int i=0; i<userDto.getAddresses().size(); i++){
//            AddressDto addressDto = userDto.getAddresses().get(i);
//            AddressEntity addressEntity = modelMapper.map(addressDto, AddressEntity.class);
//            addressRepository.delete(addressEntity);
//        }

    }

}
