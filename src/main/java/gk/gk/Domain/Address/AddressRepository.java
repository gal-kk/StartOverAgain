package gk.gk.Domain.Address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    void deleteByAddressId(String addressId);
    AddressEntity findByAddressId(String addressId);
}
