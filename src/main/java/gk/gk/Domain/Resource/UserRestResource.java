package gk.gk.Domain.Resource;

import gk.gk.Domain.Address.AddressRest;
import gk.gk.Domain.User.UserRest;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class UserRestResource extends ResourceSupport {
    @Getter
    private String userId;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Getter
    private String email;

    @Getter
    private List<AddressRestResource> addresses;

    public UserRestResource(UserRest userRest){
        this.userId = userRest.getUserId();
        this.firstName = userRest.getFirstName();
        this.lastName = userRest.getLastName();
        this.email = userRest.getEmail();
        List<AddressRest> addressO = userRest.getAddresses();
        this.addresses = new AddressInfoAssembler().toResources(addressO);
    }

}
