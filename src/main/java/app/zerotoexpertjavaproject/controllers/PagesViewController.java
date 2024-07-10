package app.zerotoexpertjavaproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesViewController {

    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }



}
