package korweb.service;

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
            loginstatus = true;
            return true;
        }
        else{
            System.out.println("로그인실패");
            return false;
        }


    }



}
