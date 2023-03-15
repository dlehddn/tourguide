package com.tourguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourguide.domain.dto.MemberDto;
import com.tourguide.domain.entity.MemberEntity;
import com.tourguide.domain.entity.MemberRepository;

import jakarta.transaction.Transactional;


@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public int setmember(MemberDto memberDto){

       MemberEntity memberEntity =  memberRepository.save(memberDto.toEntity()); 
       return memberEntity.getMno();    
    }
    
}
