package gk.gk;

import gk.gk.Domain.User.UserDetailRequestModel;
import gk.gk.Domain.User.UserEntity;
import gk.gk.Domain.User.UserRest;
import org.springframework.web.client.RestTemplate;

public class ConsumeApi {
    private static RestTemplate restTemplate = new RestTemplate();

    public static UserRest createUser(UserDetailRequestModel userDetailRequestModel){
        return restTemplate.postForObject("http://localhost:8080/users", userDetailRequestModel, UserRest.class);
    }
}