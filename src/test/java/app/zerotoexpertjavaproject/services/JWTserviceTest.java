package app.zerotoexpertjavaproject.services;

import app.zerotoexpertjavaproject.entities.Permission;
import app.zerotoexpertjavaproject.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

//ta adnotacja w springu daje przeladowany i skonfigurowany kontekst springa
@SpringBootTest
class JWTserviceTest {

    private final JWTservice jwTservice;

    @Autowired
    public JWTserviceTest(JWTservice jwTservice){
        this.jwTservice = jwTservice;
    }

    //metoda_stan_efekt
    //Given-When-Then: Opisuje stan początkowy, akcję oraz oczekiwany rezultat.
    //    shouldReturnTrueWhenJwtTokenIsCorrect
    //MethodName_StateUnderTest_ExpectedBehavior: Opisuje metodę, stan i oczekiwane zachowanie.
    //Behavior Driven Development (BDD): Koncentruje się na opisywaniu zachowań
    @Test
    void shouldReturnTrueIfJwtTokenIsCorrect(){
        //given

        //retrived user from database for example:
        User userToAuth = new User("Jarek","Czuka","ape@wp.pl","examplePass123", Permission.USER);

        //you can modify token by https://jwt.io/
        String generateTokenWithUsernameAndPassword = jwTservice.generateToken(userToAuth);
        //when
        boolean isTokenValid = jwTservice.validToken(generateTokenWithUsernameAndPassword,userToAuth);
        //then
        assertTrue(isTokenValid);
    }

    @Test
    void shouldReturnFalseIfJwtTokenIsIncorrect(){
        //given

        //retrived user from database for example:
        User userToAuth = new User("Jarek","Czuka","ape@wp.pl","examplePass123", Permission.USER);
        //token is wrong, changed username before hashed
        String tokenWithUsernameEqualsJarekInPayload = jwTservice.generateToken(userToAuth);
        //when
        boolean incorecctToken = jwTservice.validToken(tokenWithUsernameEqualsJarekInPayload+"false hash",userToAuth);
        boolean correctToken = jwTservice.validToken(tokenWithUsernameEqualsJarekInPayload,userToAuth);
        //then
        assertFalse(incorecctToken);
        assertTrue(correctToken);
    }

    @Test
    void shouldReturnUsernameFromToken(){

        User userToAuth = new User("Jarek","Czuka","ape@wp.pl","examplePass123", Permission.USER);
        String token = jwTservice.generateToken(userToAuth);

        String usernameFromToken = jwTservice.extractUsernameFromPayloadSectionInToken(token);
        String usernameFromUser = userToAuth.getUsername();
        assertEquals(usernameFromUser,usernameFromToken);

    }

}