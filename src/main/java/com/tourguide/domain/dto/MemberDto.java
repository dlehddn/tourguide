package com.tourguide.domain.dto;
import com.tourguide.domain.entity.MemberEntity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class MemberDto {
    private int mno;
    private String mid;
    private String mpassword;
    private String mmbti;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
            .mno(this.mno)
            .mid(this.mid)
            .mpassword(this.mpassword)
            .mmbti(this.mmbti)
            .build();
    }
}
