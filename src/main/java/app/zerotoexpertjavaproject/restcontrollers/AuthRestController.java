package app.zerotoexpertjavaproject.restcontrollers;

import app.zerotoexpertjavaproject.Auth.AuthRequestBody;
import app.zerotoexpertjavaproject.Auth.AuthResponseBody;
import app.zerotoexpertjavaproject.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@CrossOrigin
@AllArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseBody> authenticate(@RequestBody AuthRequestBody request) {
        try{
            System.out.println(request.getUsername());
            String token = authService.generateTokenForClient(request.getUsername());
            return ResponseEntity.status(200).body(new AuthResponseBody(token,"random_refresh_token"));
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(401).build();
        }
    }







}
