package gk.gk.Domain.Resource;

import gk.gk.Domain.Address.AddressRest;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class AddressRestResource extends ResourceSupport {
    @Getter
    private String addressId;

    @Getter
    private String city;

    @Getter
    private String country;

    @Getter
    private String streetName;

    @Getter
    private String postalCode;

    @Getter
    private String type;

    AddressRestResource(AddressRest addressRest) {
        this.city = addressRest.getCity();
        this.country = addressRest.getCountry();
        this.streetName = addressRest.getStreetName();
        this.postalCode = addressRest.getPostalCode();
        this.type = addressRest.getType();
        this.addressId = addressRest.getAddressId();
    }
}
