package app.zerotoexpertjavaproject.restcontrollers;

import app.zerotoexpertjavaproject.models.AuthRequestBody;
import app.zerotoexpertjavaproject.models.AuthResponseBody;
import app.zerotoexpertjavaproject.exceptions.UserAlreadyExistsException;
import app.zerotoexpertjavaproject.userLayer.mappers.dtos.UserDTO;
import app.zerotoexpertjavaproject.userLayer.services.OAuth2Service;
import app.zerotoexpertjavaproject.userLayer.services.UserService;
import app.zerotoexpertjavaproject.userLayer.services.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    private final UserService userService;

    private final OAuth2Service oAuth2Service;

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
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the user");
        }

    }

    //If that process is successful, the app inserts the user details into the Spring Security context so that you are authenticated.
    //
    // new Set-Cookie header. This cookie (JSESSIONID by default) is a token for your authentication details for Spring (or any servlet-based) applications.
    //
    //So we have a secure application, in the sense that to see any content a user has to authenticate with an external provider (GitHub).
//    @GetMapping("/auth")
//    public ResponseEntity<String> oAuth2Login(@RequestParam String code){
//        try {
//            System.out.println("przyszedl code"+code);
//            oAuth2Service.getGitHubAccess(code);
//        }catch (JsonProcessingException e){
//            ResponseEntity.status(403).body(e.getMessage());
//        }
//        return ResponseEntity.status(200).body("Otworzymales JWT w localstora dziekuje");
//    }






}
