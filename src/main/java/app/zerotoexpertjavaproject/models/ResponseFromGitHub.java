package app.zerotoexpertjavaproject.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ResponseFromGitHub {

    private String access_token;
    private String token_type;
    private String scope;
}
