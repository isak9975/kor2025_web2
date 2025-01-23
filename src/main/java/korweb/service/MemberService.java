package korweb.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import korweb.model.dto.MemberDto;
import korweb.model.entity.MemberEntity;
import korweb.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    static boolean loginstatus = false;

    @Autowired private MemberRepository memberRepository;

    //회원가입
    public boolean regist(MemberDto memberDto){
        //1. 저장할 Dto를 entity로 변환한다.
        MemberEntity memberEntity = memberDto.toEntity();
        //2. 변환된 entity를 save한다.
        //3. save(영속속/연결된) 엔티티의 회원번호가 0보다 크면 성공
        MemberEntity result = memberRepository.save(memberEntity);

        if(result.getMno()>0){
            return true;
        }

        //아니면 실패
        return false;

    }

//    로그인
    public boolean login(MemberDto memberDto){
//
//        //[ 방법1 ]
//        //(1) 모든 회원 엔티티를 조회한다.
//         List<MemberEntity> memberEntityAll = memberRepository.findAll();
//         //(2) 모든 회원 엔티티를 하나씩 조회한다.
//         for(int index=0;index >= memberEntityAll.size()-1;index++){
//             //==> forEach()문법 익히면 코드가 더욱 간단해 질듯
//             if(memberEntityAll.get(index).getMid().equals(memberDto.getMid())){ //아이디 판독
//                 if(memberEntityAll.get(index).getMpwd().equals(memberDto.getMpwd())){ //비밀번호 판도
//                     System.out.println("로그인 성공");
//                     return true;
//                 }
//             }
//         }
//        System.out.println("아이디 또는 비밀번호가 다릅니다");
//        return false;

        //[ 방법 2]
        boolean result = memberRepository.existsByMidAndMpwd(memberDto.getMid(),memberDto.getMpwd());

        if(result == true){
            System.out.println("로그인성공");
            setSession(memberDto.getMid()); // 로그인 성공시 세션에 아이디 저장
            return true;
        }
        else{
            System.out.println("로그인실패");
            return false;
        }



    }

    //===================================세션 관련 함수==================================================//


    @Autowired private HttpServletRequest request;

    //[3] 세션객체내 정보 추가 :  세선객체에 로그인된 회원아이디를 추가하는 함수 ( 로그인)
    public boolean setSession(String mid){
        //(2) 요청 객체를 이용한 톰캣내 세션 객체를 반환한다.
        HttpSession httpSession = request.getSession();
        //(3) 세션 객체에 속성(새로운 값) 추가한다.
        httpSession.setAttribute("loginId",mid);
        return true;
    }


    //[4] 세션객체내 정보 반환 : 세선에 로그인된 회원아이디를 조회
    public String getSession(){
        HttpSession httpSession = request.getSession();
        //(4) 세션 객체에 속성명의 값 반환한다.
        Object object = httpSession.getAttribute("loginId");

                if(object != null){
                    String mid = (String)object; //형변환
                    return mid;
                }

                return null;
    }

    //[5] 세션객체내 정보 초기화 : 로그아웃
    public boolean deleteSession(){

        HttpSession httpSession = request.getSession();

        httpSession.removeAttribute("loginId");

        return true;
    }

    //[6] 현재 로그인된 회원의 정보 조회
    public MemberDto getMyInfo(){
        System.out.println("getMyInfo실행");
        //1. 현재 세션에 저장된 회원 아이디 조회
        String mid = getSession(); // 현재 로그인된 아이디 조회
        //2. 만약에 로그인상태이면
        if(mid != null){
            //3. 회원아이디로 엔티티 조회 => 원래는 아이디로 조회 못하고 회원번호로만 조회가 가능했음(기본기능)
            MemberEntity memberEntity = memberRepository.findByMid(mid);
            //4. entity --> dto 변환
            MemberDto memberDto = memberEntity.toDto();
            //5. 반환
            return memberDto;
        }
        //비로그인 상태이면
        return null;
    }

    //[7] 현재 로그인된 회원 탈퇴
    public boolean myDelete(){
        System.out.println("myDelete실행");
        String mid = getSession(); // 1. 현재 세션에 저장된 회원 아이디 조회

        if(mid != null){//2. 만약에 로그인 상태이면
            //3. 현재 로그인된 아이디로 엔티티 조회
            MemberEntity memberEntity = memberRepository.findByMid(mid);
            //4. 탈퇴/삭제 실행
            memberRepository.delete(memberEntity);
            //5. 반환
            deleteSession(); //**로그인 정보 지우기 : 로그아웃
            return true;
        }
        //비로그인 상태이면
        return false;

    }

    //[8] 현재 로그인된 회원 정보 수정 , mname 닉네임, memail 이메일
    @Transactional //회원정보 수정일경우 꼭 써야함.
    public boolean myUpdate(MemberDto memberDto){
        System.out.println("update실행");
        String mid = getSession();

        if(mid != null){//로그인 상태이면
            MemberEntity memberEntity = memberRepository.findByMid(mid);

           memberEntity.setMname(memberDto.getMname());
           memberEntity.setMemail(memberDto.getMemail());
           return true;
        }
        //비로그인 상태이면
        return false;
    }

}
