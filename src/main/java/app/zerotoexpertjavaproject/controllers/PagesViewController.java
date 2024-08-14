package app.zerotoexpertjavaproject.controllers;

import app.zerotoexpertjavaproject.userLayer.services.OAuth2Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PagesViewController {

    private final OAuth2Service oAuth2Service;
    
    @GetMapping("/")
    public String getMainPage(){
        return "mainPage";
    }

    @GetMapping("/logowanie")
    public String getLoginFormPage(){
        return "loginForm";
    }
    @GetMapping("/account")
    public String getPersonalAccountPage(){
        return "account";
    }

    @GetMapping("/error")
    public String getErrorlPage(){
        return "errorPage";
    }


    @GetMapping("/auth")
    public String oAuth2Login(@RequestParam String code){
        try {
            System.out.println("przyszedl code"+code);
            oAuth2Service.getGitHubAccess(code);
        }catch (JsonProcessingException e){
            ResponseEntity.status(403).body(e.getMessage());
        }
        return "oauth-success";
    }
}
