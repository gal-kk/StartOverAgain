package gk.gk.Domain.Resource;

import gk.gk.Domain.User.UserRest;
import gk.gk.UserController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserInfoAssembler extends ResourceAssemblerSupport<UserRest, UserRestResource> {
    public UserInfoAssembler() {
        super(UserController.class, UserRestResource.class);
    }

    @Override
    protected UserRestResource instantiateResource(UserRest entity) {
        return new UserRestResource(entity);
    }

    @Override
    public UserRestResource toResource(UserRest userRest) {
        return createResourceWithId(userRest.getUserId(), userRest);
    }


}
