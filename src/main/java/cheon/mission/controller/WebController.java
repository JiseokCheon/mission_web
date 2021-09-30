package cheon.mission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/password")
    public String password(){
        return "password";
    }

    @GetMapping("/layout/static")
    public String layout_navigation(){
        return "layout-static";
    }

    @GetMapping("/layout/sidenav")
    public String layout_sidenav(){
        return "layout-sidenav-light";
    }

    @GetMapping("/charts")
    public String charts(){
        return "charts";
    }

    @GetMapping("/tables")
    public String tables(){
        return "tables";
    }

}
