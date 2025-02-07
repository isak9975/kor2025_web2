package korweb.service;

import korweb.model.dto.BoardDto;
import korweb.model.dto.MemberDto;
import korweb.model.entity.BoardEntity;
import korweb.model.entity.CategoryEntity;
import korweb.model.entity.MemberEntity;
import korweb.model.repository.BoardRepository;
import korweb.model.repository.CategoryRepository;
import korweb.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired private BoardRepository boardRepository; // 보드 엔티티 조작하는 인터페이스
    @Autowired private MemberRepository memberRepository; // 멤버 엔티티 조작하는 인터페이스
    @Autowired private CategoryRepository categoryRepository; // 카테고리 엔티티 조작하는 인터페이스

    @Autowired private MemberService memberService;

    ///1. 게시물 작성
    public boolean boardWrite(BoardDto boardDto){

        BoardEntity boardEntity = boardDto.toEntity();

        //(1) 사용자로부터 전달받은 boardDto(btitle, bcontent,cno) 를 엔티티로 변환
            //1. 게시물 작성자는 현재 로그인된 회원 이므로 세션에서 현재 로그인된 회원번호 조회

            //- 현재 로그인된 세션 객체 조희
            MemberDto loginDto = memberService.getMyInfo();

            //-만약에 로그인된 상태가 아니면 글쓰기 종료
                if(loginDto == null){
                    System.out.println("로그인이 안되어있습니다");
                    return false;
                }

            // 로그인된 상태임녀 회원번호 조회
            int loginMno = memberService.getMyInfo().getMno();

            // - 로그인된 회원번호로 회원 엔티티를 호출 및 게시물 엔티티에 대입한다.
            MemberEntity loginEntity = memberRepository.findById(loginMno).get();
        boardEntity.setMemberEntity(loginEntity);

            //2. 게시물 카테고리는 cno 를 entity 를 조회해서 게시물 엔티티에 대입한다.
                    //findById( pk 번호 ) : 지정한 pk 번호에
            CategoryEntity categoryEntity = categoryRepository.findById(boardDto.getCno()).get();
        boardEntity.setCategoryEntity(categoryEntity);


        //(2) 엔티티 save( 저장할 엔티티 )
        BoardEntity saveEntity = boardRepository.save(boardEntity);

        if(saveEntity != null){
            System.out.println("게시판 저장완료");
            return true;
        }
        else{System.out.println("[오류]게시판 저장 실패");
            return false;
        }

    }



    ///2. 게시물 전체 조회
    public List<BoardDto> boardFindAll(){
        //(1) 모든 게시물의 엔티티를 조회
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        //(2) 모든 게시물의 엔티티를 Dto 로 변환
            //1) Dto 를 저장할 리스트 선언
            List<BoardDto> boardDtoList = new ArrayList<>();
            //2) 리스트변수명.forEach( 반복변수명 -> {실행문;}
                boardEntityList.forEach(entity -> {
                    //BoardDto boardDto = entity.toDto(); 한번 거치지 않고 바로
                    boardDtoList.add(entity.toDto());
                });
        return boardDtoList;
    }



    ///3. 게시물 개별 조회
    public BoardDto boardFind(int bno){
        //(1) 조회할 특정 게시물의 번호를 매개변수로 받는다 int bno
        //(2) 조회할 특정 게시물의 번호의 엔티티를 조회한다. findById() 메소드는 반환타입이
        //***Optional 이다. 조회된 엔티티 여부 메소드 제공한다. .isPresent()***
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        //(3) 만약에 조회된 엔티티가 있으면
        if(optional.isPresent()){
            //(4) optional 에서 엔티티 꺼내기 .get()
            BoardEntity boardEntity = optional.get();
            //(5) 엔티티를 dto 로 변환
            BoardDto boardDto = boardEntity.toDto();
            //(6) dto 결과 반환
            return boardDto;
        }
        System.out.println("오류 반환값 없음");
        return null;
    }



    ///4. 게시물 수정
    public boolean boardUpdate(BoardDto boardDto){
        return false;
    }



    ///5. 게시물 삭제
    public boolean boardDelete(int bno){

        if( memberService.getMyInfo().getMno() == boardRepository.findById(bno).get().getMemberEntity().getMno() || bno == 1 ){
            boardRepository.deleteById(bno);
            return true;
        }
        System.out.println("작성자만 가능합니다.");
        return false;
    }

}
//11  10.40