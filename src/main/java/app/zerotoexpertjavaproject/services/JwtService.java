package app.zerotoexpertjavaproject.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class JwtService {

    private final String secretKey = "exampleSecretKey";
    private final Algorithm algorithm = Algorithm.HMAC256(secretKey);

    public String generateToken(UserDetails userDetails){

        Date date = new Date();
        Timestamp createdTokenTime = new Timestamp(date.getTime());
        //added 10 minut to createdTokenTime
        long tenMinutesInMillis = 10 * 60 * 1000;
        Timestamp expiredTokenTime = new Timestamp(createdTokenTime.getTime()+tenMinutesInMillis);


        return JWT.create()
                .withClaim("username",userDetails.getUsername())
                .withClaim("createTime", createdTokenTime)
                .withClaim("expiredTime",expiredTokenTime)
                .sign(algorithm);
    }
    public boolean verifyToken(String token, UserDetails userDetails){
        String usernameFromJWT = JWT.decode(token).getClaim("username").asString();
        //added new claim to verify like expiredTime and createdTime

        return generateToken(userDetails).equals(token)
                && userDetails.getUsername().equals(usernameFromJWT);
    }

    public String extractUsernameFromPayloadSectionInToken(String token){
        return JWT.decode(token).getClaim("username").asString();
    }






}
