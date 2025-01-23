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
    private int mno; // 회원번호

    @Column(columnDefinition = "varchar(30)",nullable = false,unique = true)
    private String mid; // 회원 아이디

    @Column(columnDefinition = "varchar(30)",nullable = false)
    private String mpwd; // 회원 비밀번호

    @Column(columnDefinition = "varchar(20)",nullable = false)
    private String mname; // 회원 닉네임

    @Column(columnDefinition = "varchar(50)",nullable = false,unique = true)
    private String memail; // 회원 이메일

    @Column(columnDefinition = "varchar(255)",nullable = false)
    private String mimg; // 회원 프로필 사진

    public MemberDto toDto(){
        return MemberDto.builder().mno(this.mno).mid(this.mid).mpwd(this.mpwd).memail(this.memail).mname(this.mname).mimg(this.mimg).build();
    }



}
