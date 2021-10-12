package cheon.mission.service;

import cheon.mission.domain.Mission;
import cheon.mission.domain.Posting;
import cheon.mission.domain.UserMission;
import cheon.mission.repository.PostingRepository;
import cheon.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostingService {

    private final PostingRepository postingRepository;
    private final UserMissionRepository userMissionRepository;

    @Transactional
    public Long save(Posting posting) {
        userMissionRepository.updatePostingCheck(posting.getUser().getId(), posting.getMission().getId());
        return postingRepository.save(posting);
    }

    public List<Posting> findPostingByMissionId(Long missionId) {
        List<Posting> findPostingList = postingRepository.findPostingByMissionId(missionId);
        return findPostingList;
    }

    public List<Posting> findPostingByUserId(Long userId) {
        List<Posting> findPostingList = postingRepository.findPostingByUserId(userId);
        return findPostingList;
    }

    @Transactional
    public int deletePosting(Long postingId) {
        int id = postingRepository.deletePosting(postingId);
        return id;
    }

    @Transactional
    public int deletePostingByMissionId(Long missionId) {
        return postingRepository.deletePostingByMissionId(missionId);
    }
}
