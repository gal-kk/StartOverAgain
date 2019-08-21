package gk.gk.Domain.Address;

import gk.gk.Domain.User.UserDto;

public interface AddressService {
    void purgeByUserDetail(UserDto userDto);

    AddressDto findByAddressId(String addressId);
}
