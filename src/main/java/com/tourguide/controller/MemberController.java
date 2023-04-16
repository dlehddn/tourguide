package com.tourguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.domain.dto.MemberDto;
import com.tourguide.service.MemberService;


@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired 
    private MemberService memberService;

    @GetMapping("/signup")
    public Resource getsignupPage(){

        return new ClassPathResource("templates/member/signup.html");
    }
    @GetMapping("/login")
    public Resource getloginPage(){

        return new ClassPathResource("templates/member/login.html");
    }
    @PostMapping("/setmember")
    public int setmember(MemberDto memberDto){
        System.out.println(
            memberDto.toString()
        );
        return memberService.setmember(memberDto);
    }
    @GetMapping("/getmember")
    public int getmember(String mid ,String mpassword){
      return memberService.getmember(mid, mpassword); 
    }
}
