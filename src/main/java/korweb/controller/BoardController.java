package korweb.controller;

import korweb.model.dto.BoardDto;
import korweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired private BoardService boardService;

//1. 게시물 작성
    @PostMapping("/board/write.do")
    public boolean boardWrite(@RequestBody BoardDto boardDto){
        return boardService.boardWrite(boardDto);
    }

//2. 게시물 전체 조회
    @GetMapping("/board/findall.do")
    public List<BoardDto> boardFindAll(){
        return boardService.boardFindAll();
    }

//3. 게시물 개별 조회
    @GetMapping("/board/find.do")
    public BoardDto boardFind(@RequestParam int bno){
        //@RequestParam 은 없어도 돌아가지만 그래도 진행
        return boardService.boardFind(bno);
    }

//4. 게시물 수정
    @PutMapping("/board/update.do")
    public boolean boardUpdate(@RequestBody BoardDto boardDto){
        return boardService.boardUpdate(boardDto);
    }

//5. 게시물 개별 삭제
    @DeleteMapping("/board/delete.do")
    public boolean boardDelete(@RequestParam int bno){
        return boardService.boardDelete(bno);
    }

}
