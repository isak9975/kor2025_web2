package korweb.model.entity;

import jakarta.persistence.*;
import korweb.model.dto.BoardDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter@Builder
@AllArgsConstructor@NoArgsConstructor
@Entity@Table(name="board")
public class BoardEntity extends BaseTime{

    //1. 게시물 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;

    //2. 게시물 제목
    @Column(columnDefinition = "varchar(255)")
    private String btitle;

    //3. 게시물 내용
    @Column(columnDefinition = "longtext")
    private String bcontent;

    //4. 게시물 조회수
    @Column(columnDefinition = "int")
    private int bview;

    //5. 작성자(FK)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mno")
    private MemberEntity memberEntity;

    //6. 카테고리번호(FK)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cno")
    private CategoryEntity categoryEntity;

    //양방향?
//    @OneToMany(mappedBy = "rcontent")
//    List<ReplyEntity> replyEntityList = new ArrayList<>();

    // entity --> Dto 변환 메소드
    // 데이터베이스 저장된 entity 를 조회한 후 dto로 변환해야 하므로 필요
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.btitle)
                .bview(this.bview)
                .mno(this.memberEntity.getMno())
                .cno(this.categoryEntity.getCno())
                .mid(this.memberEntity.getMid())
                .cname(this.categoryEntity.getCname())
                .cdate(this.getCdate().toString())
                .build();
    }



}
