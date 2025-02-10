package korweb.service;

import korweb.model.dto.BoardDto;
import korweb.model.dto.MemberDto;
import korweb.model.entity.BoardEntity;
import korweb.model.entity.CategoryEntity;
import korweb.model.entity.MemberEntity;
import korweb.model.entity.ReplyEntity;
import korweb.model.repository.BoardRepository;
import korweb.model.repository.CategoryRepository;
import korweb.model.repository.MemberRepository;
import korweb.model.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardService {

    @Autowired private BoardRepository boardRepository; // 보드 엔티티 조작하는 인터페이스
    @Autowired private MemberRepository memberRepository; // 멤버 엔티티 조작하는 인터페이스
    @Autowired private CategoryRepository categoryRepository; // 카테고리 엔티티 조작하는 인터페이스
    @Autowired private ReplyRepository replyRepository;

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


//            replyRepository.findBybno(bno);
        // * 현재 게시물의 댓글 리스트 조회
                //1. 모든 게시물 댓글 조회한다.
            List<ReplyEntity> replyEntityList = replyRepository.findAll();
                // 모든 댓글을 Dto / MAp 으로 변환한 객체들을 저장할 리스트 선언
                //2. 모든 댓글을 Dto로 변환시킨다 --> ReplyDto 대신 MAP 컬렉션 이용한 방법
                    //List 컬렉션 : [값, 값, 값] vs MAP 컬렉션 : {key:value, key:value}
            List<Map<String,String>> replyList = new ArrayList<>();

            //3. 엔티티 MAP로 변환

            replyEntityList.forEach((reply)->{
                //* 만약에 현재 조회중인 게시물 번호와 댓글리스트내 반복중인 댓글의 게시물번호와 같다면
                if(reply.getBoardEntity().getBno()==bno){
                //4. map 객체 선언
                Map<String,String> map = new HashMap<>();
                //5. map 객체에 하나씩 key:value (엔트리) 으로 저장한다.
                map.put("rno",reply.getRno()+""); // 숫자타입 + "" ==> 문자타입으로 변환
                map.put("rcontent",reply.getRcontent());
                map.put("cdate",reply.getCdate().toLocalDate().toString()); // 날짜와 시간 중에 날짜만 추출
                map.put("mid",reply.getMemberEntity().getMid()); // 댓글 작성자 아이디
                map.put("mimg",reply.getMemberEntity().getMimg()); // 댓글 작성자 프로필
                //6. map 을 리스트에 담는다.
                replyList.add(map);
                }
            });
            //7. 반복문 종료된 후 boardDto 에 댓글 리스트 담기
            boardDto.setReplylist(replyList);

            //(6) dto 결과 반환
            return boardDto;
        }
        System.out.println("오류 반환값 없음");
        return null;
    }



    ///4. 게시물 수정
    public boolean boardUpdate(BoardDto boardDto){
        return false;
    }//end f



    ///5. 게시물 삭제
    public boolean boardDelete(int bno){

        if( memberService.getMyInfo().getMno() == boardRepository.findById(bno).get().getMemberEntity().getMno() || bno == 1 ){
            boardRepository.deleteById(bno);
            return true;
        }
        System.out.println("작성자만 가능합니다.");
        return false;
    }//end f

//========================================댓글===================================================================================
    //댓글 서비스 따로 만들어도 되는데 몇개 없을 것 같아서 그냥 같이 씀
    //[6] 댓글쓰기
    public boolean replyWrite(Map<String,String> replyDto){

        //1. 현재 로그인된 회원 정보 조회
        MemberDto memberDto = memberService.getMyInfo();
        //2. 만약에 로그인된 정보가 없으면 함수 종류
        if(memberDto==null){return false;}

        //[ 로그인 중이면 ]
        //3. 회원엔티티 조회
        MemberEntity memberEntity = memberRepository.findById(memberDto.getMno()).get();
        //3. 현재 작성할 댓글이 위치한 조회중인 게시물 엔티티 조회
            //Integer.parseInt("문자열") 문자열타입 ==> 정수타입 반환 함수.
        int bno = Integer.parseInt(replyDto.get("bno"));
        BoardEntity boardEntity = boardRepository.findById(bno).get();
        //4. 입력받은 매개변수 map을 entity 로 변환

        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setRcontent(replyDto.get("rcontent")); // 댓글 내용 등록
        replyEntity.setMemberEntity(memberEntity); // 작성자 등록
        replyEntity.setBoardEntity(boardEntity); // 댓글 위치한 게시물 등록

        ReplyEntity saveEntity = replyRepository.save(replyEntity);

        if(saveEntity.getRno()>0){return true;} // 댓글번호 생성 되었다면 등록 성공.
        return false;//
    }

    //[7] 특정 게시물의 댓글 전체 조회
    public List<Map<String,String>> replyFindAll(int bno){

        //1. 모든 댓글 엔티티 조회
        List<ReplyEntity> replyEntityList = replyRepository.findAll();
        //2. 모든 댓글 map 저장할 list 선언
        List<Map<String,String>> map = new ArrayList<>();

        //3.
        replyEntityList.forEach((reply) -> {
            if(reply.getBoardEntity().getBno()==bno){
                //4. map 객체 선언
                Map<String,String> mapp = new HashMap<>();
                //5. map 객체에 하나씩 key:value (엔트리) 으로 저장한다.
                mapp.put("rno",reply.getRno()+""); // 숫자타입 + "" ==> 문자타입으로 변환
                mapp.put("rcontent",reply.getRcontent());
                mapp.put("cdate",reply.getCdate().toLocalDate().toString()); // 날짜와 시간 중에 날짜만 추출
                mapp.put("mid",reply.getMemberEntity().getMid()); // 댓글 작성자 아이디
                mapp.put("mimg",reply.getMemberEntity().getMimg()); // 댓글 작성자 프로필
                //6. map 을 리스트에 담는다.
                map.add(mapp);
            }
        });

        return null;
    }


}//end class
//11  10.40