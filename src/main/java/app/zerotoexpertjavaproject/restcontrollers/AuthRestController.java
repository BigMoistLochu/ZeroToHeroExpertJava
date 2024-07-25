package app.zerotoexpertjavaproject.restcontrollers;

import app.zerotoexpertjavaproject.Auth.AuthRequestBody;
import app.zerotoexpertjavaproject.Auth.AuthResponseBody;
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

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseBody> authenticate(@RequestBody AuthRequestBody request) {

        //403 jesli nie uda sie zautoryzowac
        //200 jesli sie uda i zwrot tokenu plus header Location dla

        return ResponseEntity.status(200).header("Location","secure").body(new AuthResponseBody("hash123.hash123.hash123"));
    }





}
