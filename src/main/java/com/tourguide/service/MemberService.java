package com.tourguide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.domain.dto.MemberDto;
import com.tourguide.domain.entity.MemberEntity;
import com.tourguide.domain.entity.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;


@Service
public class MemberService {
    @Autowired // 스프링 컨테이너 [ 메모리 ] 위임
    private HttpServletRequest request ;            // 요청 객체
    
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public int setmember(MemberDto memberDto){

       MemberEntity memberEntity =  memberRepository.save(memberDto.toEntity()); 
       return memberEntity.getMno();    
    }

    @Transactional
    public int getmember( String mid ,String mpassword){
        System.out.println("입력받은 아이디:"+mid);
        System.out.println("입력받은 비밀번호:"+mpassword);
      List<MemberEntity> memlist = memberRepository.findAll();
      System.out.println(memlist);
           // 2. 입력받은 데이터와 일치값 찾기
        for( MemberEntity entity : memlist ){ // 리스트 반복
            if(entity.getMid().equals(mid)){ // 엔티티=레코드 의 이메일 과 입력받은 이메일
                if( entity.getMpassword().equals(mpassword)){ // 엔티티=레코드 의 패스워드 와 입력받은 패스워드
                    System.out.println("db에있는 아이디"+entity.getMid());
                    System.out.println("db에있는 비밀번호"+entity.getMpassword());
                    request.getSession().setAttribute("loginMno" , entity.getMno() );
                                                                // 엔티티 = 레코드 = 로그인 성공한객체
                    return 1;// 로그인 성공했다.
                }
               else return 2; // 패스워드 틀림 [ 전제조건 : 아이디중복 없다는 전제조건
            }
        }
       return 0; // 아이디가 틀림
    }
    
    
    
}
