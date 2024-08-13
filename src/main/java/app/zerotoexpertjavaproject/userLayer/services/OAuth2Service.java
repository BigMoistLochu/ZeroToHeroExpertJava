package app.zerotoexpertjavaproject.userLayer.services;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuth2Service {

    //servis odpowiedzialny za wysylanie i logowanie uzytkownika za pomoca oauth protokolu



//    String authorizationUrl = String.format(
//            "https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s",
//            client_ID, REDIRECT_URI, SCOPE);


    //z kodem ktory dostales uderzasz pod podane api...
    //1.z podanym kodem potrzebujesz dostac sie do acces_tokenu
    //2.acces_token wykorzystac do uderzenia po dane uzytkownika:
    //Authorization: Bearer OAUTH-TOKEN
    //GET https://api.github.com/user
    //z danych uzytkownika musimy utworzyc jwt i wsadzic je po stronie klienta

    public void getGitHub(String code){
        GitHubOAuth.getAccessToken(code);
    }



}

class GoogleOAuth{

}

class GitHubOAuth{
    private static String client_ID = "Ov23liFFZf0jP1qF0dl2";
    private static String REDIRECT_URI = "http://localhost:8080/auth";
    private static String SCOPE = "user";

    private static String client_secret = System.getenv("client_secret");

    public static void getAccessToken(String code){

        RestTemplate restTemplate = new RestTemplate();

        // Tworzenie nagłówków
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        //URL, ZMIENIC client_id,client_secret oraz REDIRECT_URI ktory ustawilo sie w swojej aplikacji github

        String url = "https://github.com/login/oauth/access_token"
                + "?client_id="+client_ID
                + "&client_secret="+client_secret
                + "&code="+code
                + "&redirect_uri="+REDIRECT_URI;

        // Tworzenie HttpEntity tylko z nagłówkami (ciało nie jest potrzebne w tym przypadku)
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Wysyłanie POST
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Sprawdzanie odpowiedzi
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Response: " + response.getBody());
        } else {
            System.out.println("Request failed: " + response.getStatusCode());
        }

    }

    public static void getUserDetails(String accessToken){

    }

    public static void checkUser(){
        //jesli jest w bazie danych to stworz jwt
        //jesli nie ma go w bazie danych zarejestruj go
    }
}
