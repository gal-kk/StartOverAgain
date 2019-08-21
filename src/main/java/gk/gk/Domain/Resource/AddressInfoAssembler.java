package gk.gk.Domain.Resource;

import gk.gk.Domain.Address.AddressRest;
import gk.gk.UserController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class AddressInfoAssembler extends ResourceAssemblerSupport<AddressRest, AddressRestResource> {
    public AddressInfoAssembler() {
        super(UserController.class, AddressRestResource.class);
    }

    @Override
    protected AddressRestResource instantiateResource(AddressRest entity) {
        return new AddressRestResource(entity);
    }

    @Override
    public AddressRestResource toResource(AddressRest addressRest) {
        return createResourceWithId(addressRest.getAddressId(), addressRest);
    }
}
