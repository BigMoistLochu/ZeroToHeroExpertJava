package app.zerotoexpertjavaproject.Auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthRequestBody {

    private String email;
    private String password;

}
