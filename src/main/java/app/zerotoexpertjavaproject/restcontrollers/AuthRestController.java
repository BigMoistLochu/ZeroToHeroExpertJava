package app.zerotoexpertjavaproject.restcontrollers;

import app.zerotoexpertjavaproject.Auth.AuthRequestBody;
import app.zerotoexpertjavaproject.Auth.AuthResponseBody;
import app.zerotoexpertjavaproject.mappers.UserDTO;
import app.zerotoexpertjavaproject.services.AuthService;
import app.zerotoexpertjavaproject.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    private final UserService userService;

    /**
     * Tutaj nadawany jest token ktory uzytkownik bedzie posiadal w localStorze by nastepnie wykorzystywac go do autoryzacji do chronionych zasobow
     * @param request json ktory przychodzi z frontendu podczas logowania na endpoincie /logowanie
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseBody> authenticateUser(@RequestBody AuthRequestBody request) {
        try{

            String token = authService.authUser(request.getUsername());
            AuthResponseBody responseBody = new AuthResponseBody(token);

            return ResponseEntity.status(200)
                    .header("Location","account")
                    .body(responseBody);

        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody AuthRequestBody request){
        try{
            UserDTO userDTO = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }






}
