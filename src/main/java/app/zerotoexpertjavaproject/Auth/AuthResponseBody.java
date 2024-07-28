package app.zerotoexpertjavaproject.Auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AuthResponseBody {
    private String acces_token;
    private String refresh_token;
}
