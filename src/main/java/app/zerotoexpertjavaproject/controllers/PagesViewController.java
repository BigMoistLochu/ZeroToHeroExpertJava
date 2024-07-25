package app.zerotoexpertjavaproject.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PagesViewController {
    
    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/secure")
    public String getSecurePage(){
        return "securepage";
    }


}
