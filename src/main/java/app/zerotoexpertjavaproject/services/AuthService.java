package app.zerotoexpertjavaproject.services;

import app.zerotoexpertjavaproject.entities.userentity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final UserService userService;

    public String generateTokenForClient(String username){
        //jesli nie ma usera to rzuci bledem
        User user = userService.getUserByUsername(username);
        return jwtService.generateToken(user);
    }



    public String refreshTokenForClient(String token){

        return token;
    }



}
