package cheon.mission.controller;

import cheon.mission.auth.Dto.SessionUser;
import cheon.mission.domain.*;
import cheon.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

@RequiredArgsConstructor
@Controller
@SessionAttributes("user")
public class WebController {

    private final HttpSession httpSession;
    private final MissionService missionService;

    @GetMapping("/")
    public String home(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("username", user.getName());
            model.addAttribute("useremail", user.getEmail());
        }

        List<Mission> allMissions = missionService.findAll();

        model.addAttribute("allMissions", allMissions);

        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("username", user.getName());
            model.addAttribute("useremail", user.getEmail());
        }

        List<Mission> allMissions = missionService.findMissions(search);

        model.addAttribute("search", search);
        model.addAttribute("allMissions", allMissions);

        return "search";
    }

    @GetMapping("/login_page")
    public String login() {
        return "login_page";
    }

}
