package app.zerotoexpertjavaproject.Auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Setter
@Getter
public class AuthResponseBody {
    private String access_token;
}
