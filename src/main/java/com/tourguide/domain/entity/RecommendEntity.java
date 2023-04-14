package com.tourguide.domain.entity;
import com.tourguide.domain.dto.RecommendDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="recommendinfo")
@NoArgsConstructor
@AllArgsConstructor@Getter@Setter@ToString@Builder
public class RecommendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;

    @Column(nullable = true)
    private String rid;

    @Column(length = 50000,nullable = false)
    private String rinfo;


    public RecommendDto toDto(){
        return RecommendDto.builder()
            .rno(this.rno)
            .rid(this.rid)
            .rinfo(this.rinfo)
            .build();
    }
}
