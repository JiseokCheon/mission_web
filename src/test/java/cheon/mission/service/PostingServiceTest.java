package cheon.mission.service;

import cheon.mission.domain.*;
import cheon.mission.repository.MissionRepository;
import cheon.mission.repository.PostingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class PostingServiceTest {

    @Autowired
    PostingService postingService;
    @Autowired
    PostingRepository postingRepository;

    @Test
    @DisplayName("포스팅 생성 테스트")
    public void 포스팅테스트() {
        Posting posting = new Posting("포스팅", "내용");
        User user = new User("천지석", "cjswltjr", Role.USER);
        Mission mission = new Mission("미션 1", "미션 내용", LocalDate.now(), LocalDate.now(), "code", MissionStatus.PROGRESS);

        posting.setPostingList(mission, user);
        Long findId = postingService.save(posting);


        Posting findPosting = postingRepository.findById(findId).get();
        Assertions.assertEquals(posting, findPosting);
    }
}