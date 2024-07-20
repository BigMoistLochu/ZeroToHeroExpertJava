package app.zerotoexpertjavaproject.Auth;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    //register
    //login-> check user and genereate jwt
    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequestBody request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        //w tym momencie manager nawet po filtrze Username...Filter powinien dostac to
        //

        return "Udalo sie autoryzacje dac";
    }





}
