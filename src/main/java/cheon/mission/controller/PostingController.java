package cheon.mission.controller;

import cheon.mission.security.dto.SessionUser;
import cheon.mission.domain.Dto.JoinMissionListDto;
import cheon.mission.domain.Dto.PostingDto;
import cheon.mission.domain.Mission;
import cheon.mission.domain.Posting;
import cheon.mission.domain.User;
import cheon.mission.service.MissionService;
import cheon.mission.service.PostingService;
import cheon.mission.service.UserMissionService;
import cheon.mission.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostingController {
    private final HttpSession httpSession;
    private final MissionService missionService;
    private final UserService userService;
    private final UserMissionService userMissionService;
    private final PostingService postingService;

    @GetMapping("/posting")
    public String posting(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        List<JoinMissionListDto> missionList = userMissionService.findJoinMissionByUserId(user);

        model.addAttribute("missionList", missionList);
        model.addAttribute("postingDto", new PostingDto());

        return "posting/posting";
    }

    @PostMapping("/posting")
    public String posting(PostingDto postingDto) {
        SessionUser sUser = (SessionUser) httpSession.getAttribute("user");

        Posting posting = new Posting(postingDto.getTitle(), postingDto.getContext(), LocalDate.now());

        User user = userService.findByEmail(sUser.getEmail());
        Mission mission = missionService.findById(postingDto.getMissionId());
        posting.setPostingList(mission, user);

        postingService.save(posting);

        return "redirect:/mission/" + postingDto.getMissionId();
    }

    @GetMapping("/posting/management")
    public String postingManagement(Model model) {
        SessionUser sUser = (SessionUser) httpSession.getAttribute("user");

        User user = userService.findByEmail(sUser.getEmail());

        List<Posting> postingList = postingService.findPostingByUserId(user.getId());

        model.addAttribute("postingList", postingList);

        return "posting/postingManagement";
    }

    @DeleteMapping("/posting/delete/{postingId}")
    public String deletePosting(@PathVariable("postingId") Long postingId) {

        postingService.deletePosting(postingId);
        return "redirect:/posting/management";
    }

}
