package com.tourguide.domain.dto;
import com.tourguide.domain.entity.RecommendEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class RecommendDto {
    private int rno;
    private String rid;
    private String rinfo;
    
    public RecommendEntity toEntity(){
        return RecommendEntity.builder()
            .rno(this.rno)
            .rid(this.rid)
            .rinfo(this.rinfo)
            .build();
    }
}
