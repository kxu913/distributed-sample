package com.kevin.sample.uim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/","/index"})
    public String index(){
        return "login2";
    }
    @GetMapping("/login")
    public String login(){
        return "login2";
    }
}
