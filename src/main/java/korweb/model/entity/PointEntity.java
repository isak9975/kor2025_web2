package korweb.model.entity;

import jakarta.persistence.*;
import korweb.model.dto.PointDto;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

@Getter@Setter@Builder
@AllArgsConstructor@NoArgsConstructor
@Entity@Table(name="point")
public class PointEntity extends BaseTime{
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pno;

    @Column(columnDefinition = "varchar(30)", nullable = false)
    private String pcontent;

    @Column(nullable = false, columnDefinition = "int")
    private int pcount;

    @ManyToOne //Fk
    @JoinColumn(name="mno")
    private MemberEntity memberEntity;

    //enetity --> dto 변환함수
    public PointDto pointDto(){
        return PointDto.builder().pno(this.pno).pcontent(this.pcontent).pcount(this.pcount).cdate(this.getCdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss"))).build();
    }
}
