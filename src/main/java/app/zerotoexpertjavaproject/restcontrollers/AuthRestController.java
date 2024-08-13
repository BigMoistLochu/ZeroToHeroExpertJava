package app.zerotoexpertjavaproject.restcontrollers;

import app.zerotoexpertjavaproject.models.AuthRequestBody;
import app.zerotoexpertjavaproject.models.AuthResponseBody;
import app.zerotoexpertjavaproject.exceptions.UserAlreadyExistsException;
import app.zerotoexpertjavaproject.userLayer.mappers.dtos.UserDTO;
import app.zerotoexpertjavaproject.userLayer.services.UserService;
import app.zerotoexpertjavaproject.userLayer.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/auth")
    public String oAuth2Login(@RequestParam String code){
        System.out.println(code);
        //z kodem ktory dostales uderzasz pod podane api...
        return "sukces";
    }






}
