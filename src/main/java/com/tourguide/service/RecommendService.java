package com.tourguide.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tourguide.domain.dto.RecommendDto;
import com.tourguide.domain.entity.RecommendEntity;
import com.tourguide.domain.entity.RecommendRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class RecommendService {
    @Autowired // 스프링 컨테이너 [ 메모리 ] 위임
    private HttpServletRequest request ; 
               // 요청 객체
    @Autowired
    private RecommendRepository recommendRepository;

    @Autowired
    private MemberService memberService;
    
    @Transactional
    public int setinfo(RecommendDto recommendDto){ 
    String rid = memberService.getMno();
    recommendDto.setRid(rid); // recommendDto에 rid 설정
    RecommendEntity recommendEntity =  recommendRepository.save(recommendDto.toEntity());
     
    return recommendEntity.getRno();    
    }

}
