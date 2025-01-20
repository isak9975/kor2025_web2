package korweb.controller;

import korweb.model.dto.MemberDto;
import korweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired private MemberService memberService;

    //회원가입
    @PostMapping("/member/signup.do")
    public boolean regist(@RequestBody MemberDto memberDto){
        return memberService.regist(memberDto);
    }

    //로그인
    @PostMapping("/member/login.do")
    public boolean login(@RequestBody MemberDto memberDto){
        return memberService.login(memberDto);
    }

    //회원정보 조회
}
