package cheon.mission.service;

import cheon.mission.domain.Mission;
import cheon.mission.domain.Posting;
import cheon.mission.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostingService {

    private final PostingRepository postingRepository;

    @Transactional
    public Long save(Posting posting){
        return postingRepository.save(posting);
    }

    public List<Posting> findPostingByMissionId(Long missionId){
        List<Posting> findPostingList = postingRepository.findPostingByMissionId(missionId);
        return findPostingList;
    }

    public List<Posting> findPostingByUserId(Long userId){
        List<Posting> findPostingList = postingRepository.findPostingByUserId(userId);
        return findPostingList;
    }

    @Transactional
    public int deletePosting(Long postingId){
        int id = postingRepository.deletePosting(postingId);
        return id;
    }

    @Transactional
    public int deletePostingByMissionId(Long missionId){
        return postingRepository.deletePostingByMissionId(missionId);
    }
}
