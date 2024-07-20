package app.zerotoexpertjavaproject;

import app.zerotoexpertjavaproject.entities.Permission;
import app.zerotoexpertjavaproject.entities.User;
import app.zerotoexpertjavaproject.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class ZeroToExpertJavaProjectApplication implements CommandLineRunner {


    private UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(ZeroToExpertJavaProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Haslo: Dziku123
//        userRepository.save(new User("Jarek","Czuka","ape@wp.pl","$2a$12$9uxjqile.mIP9LWaf04h6u8JMB3QX.oza/dajLL.swRInewp9xzdO", Permission.USER));
    }
}
