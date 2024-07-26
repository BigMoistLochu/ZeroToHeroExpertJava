package app.zerotoexpertjavaproject.services;

import app.zerotoexpertjavaproject.Auth.AuthRequestBody;
import app.zerotoexpertjavaproject.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final UserService userService;

    public String generateTokenForClient(String username){
        User user = userService.getUserByUsername(username);
        return jwtService.generateToken(user);
    }



}
