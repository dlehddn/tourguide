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

    @Transactional
    public int setinfo(RecommendDto recommendDto){ 
    RecommendEntity recommendEntity =  recommendRepository.save(recommendDto.toEntity());
     
    return recommendEntity.getRno();    
    }

    @Transactional
    public String getinfo(String info_id){
    List<RecommendEntity> infolist = recommendRepository.findAll();
        for(RecommendEntity entity : infolist) {
            if(entity.getRid().equals(info_id)){
                return entity.getRinfo(); 
            }
        }
        return null;
    }
}

