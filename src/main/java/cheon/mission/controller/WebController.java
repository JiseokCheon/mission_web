package cheon.mission.controller;

import cheon.mission.auth.Dto.SessionUser;
import cheon.mission.domain.Dto.MissionDto;
import cheon.mission.domain.Mission;
import cheon.mission.domain.User;
import cheon.mission.service.MissionService;
import cheon.mission.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class WebController {

    private final HttpSession httpSession;
    private final MissionService missionService;
    private final UserService userService;

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
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        return "login_page";
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("missionDto", new MissionDto());

        return "register";
    }

    @PostMapping("/register")
    public String registerMission(MissionDto missionDto) {
        LocalDate startDate = LocalDate.parse(missionDto.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(missionDto.getEndDate(), DateTimeFormatter.ISO_DATE);

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        User user = userService.findByEmail(sessionUser.getEmail());

        Mission mission = new Mission(missionDto.getName(),
                missionDto.getContext(),
                startDate,
                endDate,
                missionDto.getCode());

        mission.setOwner(user);

        missionService.save(mission);

        return "redirect:/";
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

    @GetMapping("/charts/{missionId}")
    public String charts(@PathVariable("missionId") Long missionId, Model model) {
        Mission mission = missionService.findById(missionId);

        model.addAttribute(mission);

        return "charts";
    }

    @GetMapping("/tables")
    public String tables() {
        return "tables";
    }

}
