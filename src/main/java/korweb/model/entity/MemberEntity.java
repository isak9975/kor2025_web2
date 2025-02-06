package korweb.model.entity;

import jakarta.persistence.*;
import korweb.model.dto.MemberDto;
import lombok.*;

@Getter@Setter@Builder@ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name ="member") // 테이블이름
public class MemberEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;

    @Column(columnDefinition = "varchar(30)",nullable = false,unique = true)
    private String mid;

    @Column(columnDefinition = "varchar(30)",nullable = false)
    private String mpwd;

    @Column(columnDefinition = "varchar(20)",nullable = false)
    private String mname;

    @Column(columnDefinition = "varchar(50)",nullable = false,unique = true)
    private String memail;

    @Column( nullable = false , columnDefinition = "varchar(255)" )
    private String mimg;    // 회원 프로필사진명

    public MemberDto toDto(){
        return MemberDto.builder().mno(this.mno).mid(this.mid).mpwd(this.mpwd).memail(this.memail).mname(this.mname).mimg( this.mimg) .build();
    }



}
