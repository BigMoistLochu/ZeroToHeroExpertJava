package app.zerotoexpertjavaproject.Auth;

import app.zerotoexpertjavaproject.entities.Permission;
import app.zerotoexpertjavaproject.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authenticationManager;

    //uzywajac menadzera on deleguje to do
    //@RequestBody AuthRequestBody request
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseBody> authenticate() {

//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
        //wywolujac z kontrollera authenticationManager.authenticate jedynie sprawdzasz czy wszystko jest w porzadku, jesli nie ma takiego usera to walnie exceptionem
        //to nie rozwiazuje naszego problemu
        //jesli chcemy zrobic api miedzy frontendem a backendem to generujemy Token albo raz albo po prostu logujemy sie gdzie wciska nam


        //zwroc do elementu js
        return ResponseEntity.status(200).body(new AuthResponseBody("hash123.hash123.hash123"));
    }





}
