package data;

import java.util.Random;

public class UserGenerator {
    public User generateUser(){
        Random random = new Random();
        int number = random.nextInt(10001);
        String username = "user" + number;
        String password ="pass" + number;
        System.out.println("Generated User is: " + number);

        return new User(username , password);
    }
}
