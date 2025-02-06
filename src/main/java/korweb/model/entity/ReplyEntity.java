package korweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter@Setter@Builder
@AllArgsConstructor@NoArgsConstructor
@Entity@Table(name = "reply")
public class ReplyEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno;

    @Column(columnDefinition = "varchar(255)",nullable = false)
    private String rcontent;

    //3. 댓글 작성자 : 작성자 번호 : 단방향
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mno")
    private MemberEntity memberEntity;

    //4. 게시물번호 : 단방향
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="bno")
    private BoardEntity boardEntity;


}
