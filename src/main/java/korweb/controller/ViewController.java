package korweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// ============= 템플릿 반환 하는 컨트롤러 클래스 =========== //
@Controller
public class ViewController {

    // [1] 메인 페이지를 반환해주는 메소드
    @GetMapping("/index") // http://localhost:8080
    public String index(){   return "index.html"; }

    // [2] 로그인 페이지를 반환해주는 메소드
    @GetMapping("/member/login")
    public String login(){ return "/member/login.html"; }

    // [3] 회원가입 페이지를 반환해주는 메소드
    @GetMapping("/member/signup")
    public String signup(){ return "/member/signup.html"; }

    // [4] 마이페이지 를 반환 해주는 메소드
    @GetMapping("/member/info")
    public String myInfo(){ return "/member/info.html";}

    // [5] 수정페이지 를 반환 해주는 메소드
    @GetMapping("/member/update")
    public String myUpdate(){ return "/member/update.html";}

    //[6] 게시물 목록 페이지를 반환 해주는 메소드
    @GetMapping("/board")
    public String board(){return "/board/board.html";}

    //[7] 게시물 쓰기 페이지를 반환 해주는 메소드
    @GetMapping("/board/write")
    public String boardWrite(){return "/board/write.html";}

    //[8] 게시물 개별 조회 페이지를 반환 해주는 메소드
    @GetMapping("/board/view")
    public String boardView(){return "/board/view.html";}

    //[9] 공공 API 보여주는 페이지를 반환 해주는 메소드
    @GetMapping("/api")
    public String apiView(){return "/api.html";}

    //[10] 카카오 맵 API 보여주는 페이지를 반환 해주는 메소드
    @GetMapping("/api2")
    public String api2View(){return "/api2.html";}

    //[11] chat
    @GetMapping("/chat")
    public String chatView(){return "/chat.html";}

    //[12] 403(권한 접근 차단 매핑)error
    @GetMapping("/error403")
    public String error403(){return "/error403.html";}

//    //[12] 공공 API 보여주는 페이지를 반환 해주는 메소드2
//    @GetMapping("/api2")
//    public String kakaoView(){return "/api2.html";}


} // class end