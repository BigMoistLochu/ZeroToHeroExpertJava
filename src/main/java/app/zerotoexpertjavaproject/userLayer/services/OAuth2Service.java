package app.zerotoexpertjavaproject.userLayer.services;

import app.zerotoexpertjavaproject.models.ResponseFromGitHub;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuth2Service {

    private final GitHubOAuth gitHubOAuth = new GitHubOAuth();



    public void getGitHubAccess(String code) throws JsonProcessingException {
        String accessToken = gitHubOAuth.getAccessToken(code);
        String userDetails = gitHubOAuth.getUserDetails(accessToken);
        System.out.println(userDetails);
    }



}

class GoogleOAuth{

}

class GitHubOAuth{

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getAccessToken(String code) throws JsonProcessingException {

        String client_ID = "Ov23liFFZf0jP1qF0dl2";
        String client_secret = System.getenv("client_secret");
        String REDIRECT_URI = "http://localhost:8080/auth";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        String url = "https://github.com/login/oauth/access_token"
                + "?client_id="+client_ID
                + "&client_secret="+client_secret
                + "&code="+code
                + "&redirect_uri="+REDIRECT_URI;

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("accestoken: "+ response.getBody());
            ResponseFromGitHub responseFromGitHub = objectMapper.readValue(response.getBody(),ResponseFromGitHub.class);
            return responseFromGitHub.getAccess_token();
        } else {
            System.out.println("Request failed: " + response.getStatusCode());
            throw new RuntimeException("blad przy accesstokenie");
        }
    }



    public String getUserDetails(String accessToken){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        String url = "https://api.github.com/user";
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Response: " + response.getBody());
            return response.getBody();
        } else {
            System.out.println("Request failed: " + response.getStatusCode());
            throw new RuntimeException("");
        }

    }


}
