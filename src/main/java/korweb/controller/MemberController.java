package korweb.controller;

import korweb.model.dto.MemberDto;
import korweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //3. 현재 로그인된 회원 아이디 http 매핑
    @GetMapping("/member/login/id.do")
    public String loginId(){
        return memberService.getSession();
    }

    //4. 현재 로그인된 회원 로그아웃 http 매핑
    @GetMapping("/member/logout.do")
    public boolean logout(){
        return memberService.deleteSession();
    }

    //[6] 내정보 조회
    @GetMapping("/member/myinfo.do")
    public MemberDto myinfo(){
        return memberService.getMyInfo();
    }

    //[7] 회원탈퇴
    @DeleteMapping("/member/delete.do")
    public boolean mydelete(){
        return memberService.myDelete();
    }

    //[8] 회원정보 수정
    @PutMapping("/member/myupdate.do")
    public boolean myupdate(@RequestBody MemberDto memberDto){
        return memberService.myUpdate(memberDto);
    }

    //회원정보 조회
}
