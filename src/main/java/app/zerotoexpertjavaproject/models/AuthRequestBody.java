package app.zerotoexpertjavaproject.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthRequestBody {

    private String username;
    private String password;
    private String email;
}
