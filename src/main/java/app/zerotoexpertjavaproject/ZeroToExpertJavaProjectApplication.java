package app.zerotoexpertjavaproject;

import app.zerotoexpertjavaproject.Auth.AuthRequestBody;
import app.zerotoexpertjavaproject.Auth.AuthResponseBody;
import app.zerotoexpertjavaproject.entities.Permission;
import app.zerotoexpertjavaproject.entities.User;
import app.zerotoexpertjavaproject.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


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
