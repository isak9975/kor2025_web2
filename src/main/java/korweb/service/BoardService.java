package korweb.service;

import korweb.model.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    //1. 게시물 작성
    public boolean boardWrite(BoardDto boardDto){
        return false;
    }

    //2. 게시물 전체 조회
    public List<BoardDto> boardFindAll(){
        return null;
    }

    //3. 게시물 개별 조회
    public BoardDto boardFind(int bno){
        return null;
    }

    //4. 게시물 수정
    public boolean boardUpdate(BoardDto boardDto){
        return false;
    }

    //5. 게시물 삭제
    public boolean boardDelete(int bno){
        return false;
    }

}
