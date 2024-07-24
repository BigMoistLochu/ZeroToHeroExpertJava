package app.zerotoexpertjavaproject.services;

import app.zerotoexpertjavaproject.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class JWTservice {

    private final String secretKey = "exampleSecretKey";
    private final Algorithm algorithm = Algorithm.HMAC256(secretKey);

    public String generateToken(UserDetails userDetails){
        return JWT.create()
                .withClaim("username",userDetails.getUsername())
                .sign(algorithm);

        //na etapie generowania tokenu mozesz dac mu czas w ktorym jest aktywny
    }
    public boolean validToken(String token,UserDetails userDetails){
        String usernameFromJWT = JWT.decode(token).getClaim("username").asString();
        return generateToken(userDetails).equals(token)
                && userDetails.getUsername().equals(usernameFromJWT);
    }

    public String extractUsernameFromPayloadSectionInToken(String token){
        return JWT.decode(token).getClaim("username").asString();
    }






}
