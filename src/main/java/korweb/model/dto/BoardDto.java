package korweb.model.dto;

import korweb.model.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Setter@Getter@Builder
@AllArgsConstructor@NoArgsConstructor
public class BoardDto {

    private int bno; // 게시판 번호
    private String btitle; // 게시판 제목
    private String bcontent; // 게시판 내용
    private int bview; // 게시판 조회수

    private int mno; // 작성자의 회원번호
    private int cno; // 카테고리 번호
    private String cdate; // 작성 시간

    /**
     * 화면에는 작성자의 회원번호가 아닌 아이디를 출력해야 하므로
     */
    private String mid; // 작성자의 회원 아이디
    private String cname; // 카테고리명

    //+댓글 리스트
    private List<Map<String,String>> replylist;

    /**
     Dto --> Entity 변환 메소드
     dto 를 entity 객체로 변환해서  데이터베이스에 저장 해야 하므로 변환이 필요하다
    */
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bno(this.bno)
                .bcontent(this.bcontent)
                .btitle(this.btitle)
                .bview(this.bview)
                .build();
    }

}

