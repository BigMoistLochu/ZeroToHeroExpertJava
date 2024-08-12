package app.zerotoexpertjavaproject;


import app.zerotoexpertjavaproject.userLayer.repositories.UserRepository;
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
    }
}
