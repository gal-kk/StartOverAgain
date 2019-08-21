package gk.gk;

import gk.gk.Domain.Address.AddressRequestModel;
import gk.gk.Domain.User.UserDetailRequestModel;
import gk.gk.Domain.User.UserRest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class GkApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GkApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SpringApplicationContext springApplicationContext(){
        return new SpringApplicationContext();
    }

    @Override
    public void run(String... args) throws Exception {
        UserDetailRequestModel userDetailRequestModel = new UserDetailRequestModel();
        userDetailRequestModel.setFirstName("Yearning");
        userDetailRequestModel.setLastName("gk");
        userDetailRequestModel.setPassword("1311168");
        userDetailRequestModel.setEmail("1311168sd@gmail.com");
        List<AddressRequestModel> addresses = Arrays.asList(
                new AddressRequestModel("LA", "US", "asdfsdf", "000000", "billing"),
                new AddressRequestModel("LA", "US", "asdfsdf", "000000", "billing")
        );

        userDetailRequestModel.setAddresses(addresses);

        UserRest userRest = ConsumeApi.createUser(userDetailRequestModel);
//        System.out.printf(userRest.getEmail());
    }
}
