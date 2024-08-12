package app.zerotoexpertjavaproject.userLayer.services;

public class OAuth2Service {

    //servis odpowiedzialny za wysylanie i logowanie uzytkownika za pomoca oauth protokolu

    String client_ID = "Ov23liFFZf0jP1qF0dl2";
    String REDIRECT_URI = "http://localhost:8080/oauth";
    String SCOPE = "user";

    String authorizationUrl = String.format(
            "https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s&scope=%s",
            client_ID, REDIRECT_URI, SCOPE);









}
