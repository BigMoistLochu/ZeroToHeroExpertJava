package app.zerotoexpertjavaproject.userLayer.services;

import app.zerotoexpertjavaproject.userLayer.entities.userentity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final UserService userService;

    public String authUser(String username){
        //jesli nie ma usera to rzuci bledem
        User user = userService.getUserByUsername(username);
        return jwtService.generateToken(user);
    }





}
