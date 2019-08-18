package gk.gk;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {
    String sample = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    Random random = new SecureRandom();

    public String AddressIdGen(int length){
        StringBuilder re = new StringBuilder(length);
        for (int i = 0; i<length; i++){
            re.append(sample.charAt(random.nextInt(sample.length())));
        }
        return re.toString();
    }

    public String UserIdGen(int length){
        StringBuilder re = new StringBuilder(length);
        for (int i = 0; i<length; i++){
            re.append(sample.charAt(random.nextInt(sample.length())));
        }
        return re.toString();
    }
}
