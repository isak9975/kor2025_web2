package korweb.model.dto;

import korweb.model.entity.MemberEntity;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@Getter@Setter@Builder
@AllArgsConstructor@NoArgsConstructor
public class MemberDto {

    private int mno;

    private String mid;

    private String mpwd; // 시큐리티 암호화 하기

    private String mname;

    private String memail;

    private String mimg;

    private MultipartFile uploadfile; // 업로드 파일객체

    private String cdate;

    private String udate ;

    public MemberEntity toEntity(){
        return MemberEntity.builder().mno(this.mno).mid(this.mid)
                //+시큐리티 암호화 했을때, Bcrypt 객체를 이용한 암호화 하기
                // 회원가입 할 때 현재 builder 사용하므로 암호화가 적용된다.
                .mpwd(new BCryptPasswordEncoder().encode(this.mpwd))
                .mname(this.mname).memail(this.memail).mimg( this.mimg) .build();
    }

}
