package korweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter@Getter@Builder
@AllArgsConstructor@NoArgsConstructor
@Entity@Table(name="file")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String fname;

    //3. 첨부파일이 위치할 게시물 번호
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;

}
