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
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@RestController
@CrossOrigin
@AllArgsConstructor
public class AuthRestController {

    private final AuthService authService;


    /**
     * Tutaj nadawany jest token ktory uzytkownik bedzie posiadal w localStorze by nastepnie wykorzystywac go do autoryzacji do chronionych zasobow
     * @param request json ktory przychodzi z frontendu podczas logowania na endpoincie /logowanie
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseBody> authenticateUser(@RequestBody AuthRequestBody request) {
        try{
            //wygenerowany token ma byc przydzielony do usera
            //token ma trafic do tabeli Token
            //user loguje sie pierwsz raz wiec nie ma tokenu
            String token = authService.generateTokenForClient(request.getUsername());
            AuthResponseBody responseBody = new AuthResponseBody(token);

            return ResponseEntity.status(200)
                    .header("Location","account")
                    .body(responseBody);

        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(401).build();
        }
    }

    //rejestracja uzytkownika
    public ResponseEntity<Object> registerUser(@RequestBody AuthRequestBody request){



        return ResponseEntity.status(200).build();
    }






}
