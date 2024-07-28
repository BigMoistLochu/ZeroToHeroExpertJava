package app.zerotoexpertjavaproject.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthRequestBody {
    private String username;
    private String password;
}
