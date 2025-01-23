package korweb.model.dto;

import korweb.model.entity.MemberEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter@Setter@Builder
@AllArgsConstructor@NoArgsConstructor
public class MemberDto {

    private int mno; // 회원 번호

    private String mid; // 회원 아이디

    private String mpwd; // 회원 비밀번호

    private String mname; // 회원 닉네임

    private String memail; // 회원 이메일

    private String mimg; // 회원 프로필 사진명

    private MultipartFile uploadfile; // 업로드 파읾

    public MemberEntity toEntity(){
        return MemberEntity.builder().mno(this.mno).mid(this.mid).mpwd(this.mpwd).mname(this.mname).memail(this.memail).mimg(this.mimg).build();
    }

}
