package cheon.mission.controller;

import cheon.mission.auth.Dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class WebController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            System.out.println("user = " + user.getName());
            model.addAttribute("username", user.getName());
        }

        return "index";
    }

    @GetMapping("/login_page")
    public String login() {

        return "login_page";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }

    @GetMapping("/layout/static")
    public String layout_navigation() {
        return "layout-static";
    }

    @GetMapping("/layout/sidenav")
    public String layout_sidenav() {
        return "layout-sidenav-light";
    }

    @GetMapping("/charts")
    public String charts() {
        return "charts";
    }

    @GetMapping("/tables")
    public String tables() {
        return "tables";
    }

}
