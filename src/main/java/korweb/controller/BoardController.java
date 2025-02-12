package korweb.controller;

import korweb.model.dto.BoardDto;
import korweb.model.dto.PageDto;
import korweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BoardController {

    @Autowired private BoardService boardService;

//1. 게시물 작성
    @PostMapping("/board/write.do")
    public boolean boardWrite(@RequestBody BoardDto boardDto){
        return boardService.boardWrite(boardDto);
    }


//2. 게시물 전체 조회 //카테고리별 게시물 조회 + 페이징 처리
    @GetMapping("/board/findall.do")
    public PageDto boardFindAll(@RequestParam int cno, @RequestParam int page, @RequestParam String key,@RequestParam String keyword){
        //cno = 조회할 카테고리번호, page= 현재페이지번호,
        // key = 검색할데이터의 속성명(btitle=제목 / bcontent=내용), keyword = 검색할 데이터
        return boardService.boardFindAll(cno,page,key,keyword);
    }


//3. 게시물 개별 조회 (개별)
    @GetMapping("/board/find.do")
    public BoardDto boardFind(@RequestParam int bno){
        //@RequestParam 은 없어도 돌아가지만 그래도 진행
        return boardService.boardFind(bno);
    }

//4. 게시물 수정 (개별)
    @PutMapping("/board/update.do")
    public boolean boardUpdate(@RequestBody BoardDto boardDto){
        return boardService.boardUpdate(boardDto);
    }

//5. 게시물 개별 삭제
    @DeleteMapping("/board/delete.do")
    public boolean boardDelete(@RequestParam int bno){
        return boardService.boardDelete(bno);
    }

//========================댓글==================================================

//6. 댓글 쓰기
    @PostMapping("/reply/write.do")
    public boolean replyWrite(@RequestBody Map<String,String> replyDto){ // * Dto 클래스 대신에 map 컬렉션 활용
        return boardService.replyWrite(replyDto);
    }

//7. 특정 게시물의 댓글 전체 조회
@GetMapping("/reply/findall.do")
public List<Map<String, String>> replyFindAll(@RequestParam int bno){ // * Dto 클래스 대신에 map 컬렉션 활용
    return boardService.replyFindAll(bno);
}



}
